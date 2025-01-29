package streaming

import cats.effect.*
import fs2.{Pull, Stream}

import scala.concurrent.duration.*

object Recursion extends IOApp.Simple {
  override def run: IO[Unit] = readSse.compile.drain

  private def readSse: Stream[IO, Int] = {
    def reconnect(connectionParameters: Int): Stream[IO, Int] =
      for {
        messageStream <- clientStream.pull.uncons.flatMap {
          case Some((c, s)) => ??? // We do interesting stuff here, but it's not relevant for this example
          case None         => (Stream.sleep_[IO](1.millis) ++ reconnect(connectionParameters + 1)).pull.echo
        }.stream
      } yield messageStream

    reconnect(0)
  }

  private def clientStream: Stream[IO, Int] = Stream.empty
}

