package example

import scala.util.control.NonFatal
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback

object Consumer extends App {
    // val EXCHANGE_NAME = "slicer"
  
    // val factory = new ConnectionFactory();
    // factory.setHost("localhost");
    // try {
    //   val connection = factory.newConnection()
    //   val channel = connection.createChannel()

    //   channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    //   val queueName = channel.queueDeclare().getQueue()
    //   channel.queueBind(queueName, EXCHANGE_NAME, "");

    //   val deliverCallback: DeliverCallback  = (consumerTag, delivery) => {
    //     val message = new String(delivery.getBody(), "UTF-8");
    //     println(" [x] Received '" + message + "'");
    //   }
    //   channel.basicConsume(queueName, false, deliverCallback, consumerTag => { });

    // } catch {
    //   case NonFatal(ex) => println(ex)
    // }
}