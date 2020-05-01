package example

import scala.util.control.NonFatal
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

object Consumer extends App {
    val QUEUE_NAME = "hello";
    val factory = new ConnectionFactory();
    factory.setHost("localhost");
    try {
      val connection = factory.newConnection()
      val channel = connection.createChannel()
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);

      val deliverCallback: DeliverCallback  = (consumerTag, delivery) => {
        val message = new String(delivery.getBody(), "UTF-8");
        println(" [x] Received '" + message + "'");
      }
      channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag => { });

    } catch {
      case NonFatal(ex) => println(ex)
    }
}