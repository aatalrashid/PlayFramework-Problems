package model.postgres.driver

/**
  * Created by Eng. Chrispinus Onyancha on 19/08/2017 @ 1:59 PM for clinicPesa-v-1.
  */

import _root_.play.api._
import _root_.play.api.inject._


/**
  * Play module
  */
class PlayPlugin extends Module {
  def bindings(env: Environment, config: Configuration) = Seq(
    bind[PlayModule].toSelf.eagerly
  )
}

