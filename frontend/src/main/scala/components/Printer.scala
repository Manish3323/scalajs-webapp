package components

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.h1

@react class Printer extends StatelessComponent {

  case class Props(name: String)
  override def render: ReactElement = {
    h1(props.name)
  }
}
