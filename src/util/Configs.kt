package util

import Main
import util.encryption.Base85Decoder
import util.encryption.Base85Encoder
import java.io.*


class Configs {
    companion object {

        private var ConfigFile: File = File(System.getProperty("user.home") + "\\Desktop\\")

        private var ip = ""
        private var username = ""
        private var password = ""
        private var channel = ""

        fun save() {

            val saveFile = File(ConfigFile.absolutePath, "Config.txt")
            val isNewFileCreated :Boolean = saveFile.createNewFile()
            val saveOut = BufferedWriter(FileWriter(saveFile))

            password = Base85Encoder.Base85Encode(Send.PASSWORD.toString())

            if (Main.window.save_conn_details.isSelected) {
                saveOut.write("IP:" + Send.IP)
                saveOut.write("\r\n")
                saveOut.write("USERNAME:" + Send.USERNAME)
                saveOut.write("\r\n")
                saveOut.write("PASSWORD:$password")
                saveOut.write("\r\n")
                saveOut.write("CHANNEL:" + Send.RECEIVE)
                saveOut.write("\r\n")
            }

            saveOut.write("SAVE:" + (if (Main.window.save_conn_details.isSelected) "On" else "Off"))
            saveOut.write("\r\n")
            saveOut.write("AUTO_CONN:" + (if (Main.window.connect_on_startup.isSelected) "On" else "Off"))
            saveOut.write("\r\n")
            saveOut.write("SCHANNEL:" + Send.SUBSCRIBE)
            saveOut.write("\r\n")

            saveOut.close()
        }

        fun load() {
            val file = File(ConfigFile.absolutePath, "Config.txt")
            val stream = FileInputStream(file.absolutePath)
            val fileIn = DataInputStream(stream)
            val br = BufferedReader(InputStreamReader(fileIn))


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
                else if (content[0] == "SCHANNEL") {
                    Main.window.field_subsribe.text = content[1]
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
