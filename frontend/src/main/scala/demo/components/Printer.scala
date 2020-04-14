package demo.components

import slinky.core.StatelessComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html.h1
import slinky.web.html.div

@react class Printer extends StatelessComponent {
  case class Props(name: String)

  override def render(): ReactElement = {
    div(h1(props.name), FunctionalPrinter("String from functional component"))
  }
}
