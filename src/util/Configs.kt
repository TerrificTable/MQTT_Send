package util

import Main
import util.encryption.Base85Decoder
import util.encryption.Base85Encoder
import java.io.*

//
class Configs {
    companion object {

        var _ConfigFile: File = File(System.getProperty("user.home") + "\\Desktop\\")

        var ip = ""
        var username = ""
        var password = ""
        var channel = ""

        fun save() {

            val save_file = File(_ConfigFile.absolutePath, "Config.txt")
            val save_out = BufferedWriter(FileWriter(save_file))

            password = Base85Encoder.Base85Encode(Send.PASSWORD.toString())

            if (Main.window.save_conn_details.isSelected) {
                save_out.write("IP:" + Send.IP)
                save_out.write("\r\n")
                save_out.write("USERNAME:" + Send.USERNAME)
                save_out.write("\r\n")
                save_out.write("PASSWORD:$password")
                save_out.write("\r\n")
                save_out.write("CHANNEL:" + Send.RECEIVE)
                save_out.write("\r\n")
            }

            save_out.write("SAVE:" + (if (Main.window.save_conn_details.isSelected) "On" else "Off"))
            save_out.write("\r\n")
            save_out.write("AUTO_CONN:" + (if (Main.window.connect_on_startup.isSelected) "On" else "Off"))
            save_out.write("\r\n")

            save_out.close()
        }

        fun load() {

            val file = File(_ConfigFile.absolutePath, "Config.txt")
            val fstream = FileInputStream(file.absolutePath)
            val file_in = DataInputStream(fstream)
            val br = BufferedReader(InputStreamReader(file_in))


            var line: String? = br.readLine()
            while (line != null) {
                val content = line.split(":")

                if (content[0] == "IP") {
                    ip = content[1]
                }
                else if (content[0] == "USERNAME") {
                    username = content[1]
                }
                else if (content[0] == "PASSWORD") {
                    password = Base85Decoder.Base85Decode(content[1])
                }
                else if (content[0] == "CHANNEL") {
                    channel = content[1]
                }
                else if (content[0] == "SAVE") {
                    Main.window.save_conn_details.isSelected = content[1] == "On"
                }
                else if (content[0] == "AUTO_CONN") {
                    Main.window.connect_on_startup.isSelected = content[1] == "On"
                }

                Send.IP = ip
                Send.USERNAME = username
                Send.PASSWORD = password
                Send.RECEIVE = channel

                line = br.readLine()
            }

            var cpassword = ""
            cpassword += password.substring(0, password.length / 2)

            for (i in 0 until password.length / 2) {
                cpassword += "*"
            }

            if (Main.isWindow) {
                if (ip != "") Main.window.field_ip.text = Send.IP
                if (username != "") Main.window.field_username.text = Send.USERNAME
                if (password != "") Main.window.field_password.text = Send.PASSWORD
                if (channel != "") Main.window.field_channel.text = Send.RECEIVE
            }


            if (Main.terminal) {
                println("Configs loaded: ")
                println(" - IP:       ${Send.IP}")
                println(" - USERNAME: ${Send.USERNAME}")
                println(" - PASSWORD: $cpassword")
                println(" - CHANNEL:  ${Send.RECEIVE}")
                print("to change configs type 'c': ")
            }
        }

    }
}
