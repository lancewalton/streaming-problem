package streaming

import cats.effect.*
import cats.syntax.all.*
import fs2.{Pull, Stream}

import scala.concurrent.duration.*

object RecursionSolution extends IOApp.Simple {
  override def run: IO[Unit] = readSse.compile.drain

  private def readSse: Stream[IO, Int] = {
    def reconnect(connectionParameters: Int): Pull[IO, Int, Unit] =
      clientStream.pull.uncons.flatMap {
        case Some((c, s)) => ??? // We do interesting stuff here, but it's not relevant for this example
        case None         => Pull.sleep[IO](1.millis) *> reconnect(connectionParameters + 1)
      }

    reconnect(0).stream
  }

  private def clientStream: Stream[IO, Int] = Stream.empty
}

