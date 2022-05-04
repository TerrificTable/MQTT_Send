package util

import Main
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.json.JSONObject


class Send {
    companion object {
        var IP: String? = null
        var PORT: String? = null
        var RECEIVE: String? = "#"
        var SUBSCRIBE: String? = null
        var USERNAME: String? = null
        var PASSWORD: String? = null
        var Client: MqttClient? = null;

        fun init() {
            Client = MqttClient("tcp://$IP:$PORT", MqttClient.generateClientId(), MemoryPersistence())
            val con = MqttConnectOptions()
            con.isCleanSession = true
            con.userName = USERNAME
            con.password = PASSWORD?.toCharArray()

            println("Connecting to: $IP/$SUBSCRIBE")

            Client!!.connect(con)
        }

        fun subsrcibe() {
            try {
                Main.window.serror.text = ""

                try {
                    Client?.unsubscribe(SUBSCRIBE)
                } catch (_: Exception) { }

                Main.window.sstatus.text = "Status: Disconnected"

                Client = MqttClient("tcp://$IP:$PORT", MqttClient.generateClientId()) // , persistence
                val connOpts = MqttConnectOptions()
                connOpts.isCleanSession = true

                connOpts.userName = USERNAME
                connOpts.password = PASSWORD?.toCharArray()

                var i = 0
                val clientCallback = object : MqttCallback {
                    override fun connectionLost(cause: Throwable) {}

                    @Throws(Exception::class)
                    override fun messageArrived(topic: String, message: MqttMessage) {
                        if (i < 1) {
                            Main.window.subscribe_log.text = "${Main.window.subscribe_log.text}[#/$topic] $message\n"
                            println("[$topic] $message")
                            i = 0
                        } else {
                            i++
                        }
                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken) {}
                }

                Client!!.setCallback(clientCallback)

                println("Subscribing to: $IP/$SUBSCRIBE")

                Client!!.connect(connOpts)
                Client!!.subscribe(SUBSCRIBE)

                Main.window.sstatus.text = "Status: Connected to $SUBSCRIBE"
            } catch (e: MqttException) {
                e.printStackTrace()
                Main.window.serror.text = e.message
            }
        }

        fun send(msg: String) {

            if (msg == "") return

            val topic: MqttTopic = Client!!.getTopic(RECEIVE)

            val message = MqttMessage(JSONObject.stringToValue(msg).toString().toByteArray())
            message.qos = 2
            topic.publish(message)

            println("Message sent")

        }
    }
}
