package models

import io.bullet.borer.{Codec, Decoder, Encoder}
import io.bullet.borer.derivation.CompactMapBasedCodecs

trait MessageCodec {
  implicit lazy val MessageCodec: Codec[Message] =
    CompactMapBasedCodecs.deriveCodec
}

case class Message(value: String)
