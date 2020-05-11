package example

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import scala.util.control.NonFatal
import play.api.libs.json.Format
import play.api.libs.json.Json

case class Fact(name: String, value: String)

object Fact {
  // implicit val rw = WeePickle.macroFromTo[Fact]
  implicit val format = Json.format[Fact]
}

object Producer extends App {
  val EXCHANGE_NAME = "slicer"
  val QUEUE_NAME = "slicer";
  val factory = new ConnectionFactory();
  factory.setHost("localhost");
  try {
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.exchangeDeclare(EXCHANGE_NAME, "fanout")
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "")
    // val message = "Hello World!";
    val message = Fact("mark", "23")
    val jsonMessage = Json.toJson(message).toString.getBytes()
    while (true) {
      // channel.basicPublish(EXCHANGE_NAME, "", null, FromScala(message).transform(ToJson.bytes));
      channel.basicPublish(EXCHANGE_NAME, "", null, jsonMessage)
      println(" [x] Sent '" + message + "'");
      Thread.sleep(5000)
    }
  } catch {
    case NonFatal(ex) => println(ex)
  }
}
