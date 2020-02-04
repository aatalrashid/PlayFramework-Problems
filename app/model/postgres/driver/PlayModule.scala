package model.postgres.driver

import java.nio.charset.Charset

import javax.inject._
import _root_.play.api._
import _root_.play.api.inject._
import scalikejdbc._
import scalikejdbc.async._
import scalikejdbc.config.DBs

import scala.concurrent.Future
import scala.concurrent.duration.Duration

/**
 * The Play plugin matchantPhone use ScalikeJDBC
 */
@Singleton
class PlayModule @Inject()(lifecycle: ApplicationLifecycle, config: Configuration) {
  import model.postgres.driver.PlayModule._
  // Play DB config
  private[this] lazy val playDbConfig = config.getOptional[Configuration]("db").getOrElse(Configuration.empty)

  // ScalikeJDBC global config
  private[this] lazy val globalConfig = config.getOptional[Configuration]("scalikejdbc.global").getOrElse(Configuration.empty)

  private[this] lazy val playConfig = config.getOptional[Configuration]("scalikejdbc.play").getOrElse(Configuration.empty)

  private[this] val loggingSQLAndTime = "loggingSQLAndTime"

  private[this] var closeAllOnStop = true

  def onStart(): Unit = {
    playDbConfig.subKeys map {
      name =>
        def load(name: String): (String, String, String, AsyncConnectionPoolSettings) = {
          implicit val config = playDbConfig
          val default = AsyncConnectionPoolSettings()
          val connectionSettings = AsyncConnectionSettings(
            charset = opt(name, "charset").map(v => Charset.forName(v)),
            maximumMessageSize = opt(name, "maximumMessageSize").map(v => v.toInt),
            connectTimeout = opt(name, "connectTimeout").map(v => Duration(v)),
            testTimeout = opt(name, "testTimeout").map(v => Duration(v)),
            queryTimeout = opt(name, "queryTimeout").map(v => Duration(v))
          )
          val settings = AsyncConnectionPoolSettings(
            maxPoolSize = opt(name, "maxPoolSize").map(v => v.toInt).getOrElse(default.maxPoolSize),
            maxIdleMillis = opt(name, "maxIdleMillis").map(v => v.toLong).getOrElse(default.maxIdleMillis),
            maxQueueSize = opt(name, "maxQueueSize").map(v => v.toInt).getOrElse(default.maxQueueSize),
            connectionSettings = connectionSettings
          )
          (require(name, "url"), opt(name, "username").getOrElse(""), opt(name, "password").getOrElse(""), settings)
        }

        registeredPoolNames.synchronized {
          name match {
            case "global" =>
              // because "db.global" was used as "scalikejdbc.global" previously
              Logger(classOf[PlayPlugin]).warn("Configuration with \"db.global\" is ignored. Use \"scalikejdbc.global\" instead.")
            case _ =>
              if (!registeredPoolNames.contains(name)) {
                val (url, user, password, settings) = load(name)
                AsyncConnectionPool.add(Symbol(name), url, user, password, settings)
                registeredPoolNames.add(name)
              }
          }
        }
    }

    opt(loggingSQLAndTime, "enabled")(globalConfig).map(_.toBoolean).foreach {
      enabled =>
        implicit val config = globalConfig
        val default = LoggingSQLAndTimeSettings()
        GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
          enabled = enabled,
          singleLineMode = opt(loggingSQLAndTime, "singleLineMode").map(_.toBoolean).getOrElse(default.singleLineMode),
          logLevel = opt(loggingSQLAndTime, "logLevel").map(v => Symbol(v)).getOrElse(default.logLevel),
          warningEnabled = opt(loggingSQLAndTime, "warningEnabled").map(_.toBoolean).getOrElse(default.warningEnabled),
          warningThresholdMillis = opt(loggingSQLAndTime, "warningThresholdMillis").map(_.toLong).getOrElse(default.warningThresholdMillis),
          warningLogLevel = opt(loggingSQLAndTime, "warningLogLevel").map(v => Symbol(v)).getOrElse(default.warningLogLevel)
        )
    }

    opt("closeAllOnStop", "enabled")(playConfig).foreach { enabled => closeAllOnStop = enabled.toBoolean }
    DBs.setupAll()
  }

  def onStop(): Unit = {
    if (closeAllOnStop) {
      AsyncConnectionPool.closeAll()
      registeredPoolNames.synchronized(registeredPoolNames.clear())
    }
  }

  lifecycle.addStopHook(() => Future.successful(onStop()))
  onStart()

}

object PlayModule {

  private val registeredPoolNames = scala.collection.mutable.Set.empty[String]

  def opt(name: String, key: String)(implicit config: Configuration): Option[String] = {
    config.getOptional[String](name + "." + key)
  }

  def require(name: String, key: String)(implicit config: Configuration): String = {
    config.getOptional[String](name + "." + key) getOrElse {
      throw config.reportError(name, "Missing config [db." + name + "." + key + "]")
    }
  }

}
