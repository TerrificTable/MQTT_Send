package util

import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.json.JSONObject


class Send {
    companion object {
        var IP: String? = null
        var PORT: String? = null
        var RECEIVE: String? = null
        var SUBSRIBE: String? = null
        var USERNAME: String? = null
        var PASSWORD: String? = null
        var Client: MqttClient? = null;

        fun init() {
            Client = MqttClient("tcp://$IP:$PORT", MqttClient.generateClientId(), MemoryPersistence())
            val con = MqttConnectOptions()
            con.isCleanSession = true
            con.userName = USERNAME
            con.password = PASSWORD?.toCharArray()

            println("Connecting to: $IP")

            Client!!.connect(con)
        }

        fun subsrcibe() {
            try {
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
                            Main.window.subscribe_log.text = "${Main.window.subscribe_log.text}[$topic] $message\n"
                            println("[$topic] $message")
                            i = 0
                        } else {
                            i++
                        }
                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken) {}
                }

                Client!!.setCallback(clientCallback)

                println("Subscribing to: $IP/$RECEIVE")

                Client!!.connect(connOpts)
                Client!!.subscribe(SUBSRIBE)
            } catch (e: MqttException) {
                e.printStackTrace()
            }
        }

        fun send(msg: String) {

            if (msg == "") return

            val message = MqttMessage(JSONObject.stringToValue(msg).toString().toByteArray())
            message.qos = 2
            Client?.publish(RECEIVE, message)

            println("Message sent")

        }
    }
}
