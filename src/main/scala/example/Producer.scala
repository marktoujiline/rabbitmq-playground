package example

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import scala.util.control.NonFatal

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
    val message = "\"Hello World!\"";
    while (true) {
      // channel.basicPublish(EXCHANGE_NAME, "", null, FromScala(message).transform(ToJson.bytes));
      channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes())
      println(" [x] Sent '" + message + "'");
      Thread.sleep(5000)
    }
  } catch {
    case NonFatal(ex) => println(ex)
  }
}
