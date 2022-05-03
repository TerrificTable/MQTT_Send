package util.window

import Main
import util.Configs.Companion.save
import util.Send.Companion.Client
import util.Send.Companion.IP
import util.Send.Companion.PASSWORD
import util.Send.Companion.RECEIVE
import util.Send.Companion.USERNAME
import util.Send.Companion.init

class Buttons {
    companion object {

        fun disconnect() {
            Main.window.error.text = ""
            Main.window.button_disconnect.text = if (Client?.isConnected == true) "Connect" else "Disconnect"
            Main.window.status.text = "Status: " + if (Client?.isConnected == false) "Connected" else "Disconnected"

            try {
                if (Client?.isConnected == true) {
                    Client?.disconnect()
                }
                else if (Client?.isConnected == false || Client == null) {
                    conn()
                }
            } catch (ex: Exception) {
                println("ERROR [MQTTWin-BUTTONS]-[disconnect] (Client::isConnected): ${ex.message}")
                Main.window.error.text = ex.message
            }
        }

        fun connect() {
            Main.window.cerror.text = ""

            if (Main.window.field_ip.text != IP)
                IP = Main.window.field_ip.text
            if (Main.window.field_username.text != USERNAME)
                USERNAME = Main.window.field_username.text
            if (Main.window.field_password.text != PASSWORD)
                PASSWORD = Main.window.field_password.text
            if (Main.window.field_channel.text != RECEIVE)
                RECEIVE = Main.window.field_channel.text

            conn()
        }

        @JvmStatic
        fun conn() {
            try {
                save()
                init()
            } catch (ex: Exception) {
                println("ERROR [MQTTWIN-BUTTONS]-[connect/disconnect] (conn): $ex.message")
                Main.window.cerror.text = ex.message
            }
        }
    }
}
