package http

import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.ext.Ajax

import scala.concurrent.Future

object Client {
  def get(url: String): Future[XMLHttpRequest] = {
    Ajax
      .apply(
        method = "GET",
        url = url,
        data = "",
        timeout = 0,
        headers = Map("Content-Type" -> "application/json"),
        withCredentials = false,
        responseType = ""
      )
  }
}
