// @GENERATOR:play-routes-compiler
// @SOURCE:/home/trevor/Desktop/playProjects/my-form/conf/routes
// @DATE:Mon Feb 03 17:03:03 EAT 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
