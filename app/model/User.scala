package model

object User {


  case class UserInfo(name: String, email: String)
   implicit object UserInfo{


  }
}
