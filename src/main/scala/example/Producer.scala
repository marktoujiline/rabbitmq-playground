package example

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import scala.util.control.NonFatal

object Producer extends App {
  val QUEUE_NAME = "hello";
  val factory = new ConnectionFactory();
  factory.setHost("localhost");
  try {
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    val message = "Hello World!";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    println(" [x] Sent '" + message + "'");
  } catch {
    case NonFatal(ex) => println(ex)
  }
}
