/*
 * Created by JFormDesigner on Tue May 03 10:42:12 CEST 2022
 */

package util.window;

import org.eclipse.paho.client.mqttv3.MqttException;
import util.Send;
import util.SaveConfig;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * @author TerrificTable
 */
public class MQTTWindow extends JFrame {
    public MQTTWindow() {
        initComponents();
    }

    private void button_sendMouseClicked(MouseEvent e) {
        try {
            Send.Companion.send(field_message.getText());
        } catch (Exception exc) {
            System.out.println("ERROR [MQTTWin]-[button_send]: " + exc.getMessage());
            error.setText(exc.getMessage());
        }
    }

    private void button_connectMouseClicked(MouseEvent e) {
        Buttons.Companion.connect();
    }

    private void button_disconnectMouseClicked(MouseEvent e) {
        Buttons.Companion.disconnect();
    }

    private void button_exitMouseClicked(MouseEvent e) {
        this.dispose();
        System.exit(0);
    }

    private void thisWindowClosed(WindowEvent e) {
        SaveConfig.Companion.save();
        this.dispose();
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("util.window.resources.MQTTWindow");
        tabbedPane1 = new JTabbedPane();
        send = new JPanel();
        label1 = new JLabel();
        field_message = new JTextField();
        label2 = new JLabel();
        button_send = new JButton();
        error = new JLabel();
        button_disconnect = new JButton();
        button_exit = new JButton();
        separator1 = new JSeparator();
        status = new JLabel();
        connect = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        field_ip = new JTextField();
        field_username = new JTextField();
        field_channel = new JTextField();
        field_password = new JPasswordField();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        button_connect = new JButton();
        cerror = new JLabel();
        separator2 = new JSeparator();
        settings = new JPanel();
        label8 = new JLabel();
        connect_on_startup = new JCheckBox();
        save_conn_details = new JCheckBox();
        separator3 = new JSeparator();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MQTT Client");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {

            //======== send ========
            {
                send.setLayout(null);

                //---- label1 ----
                label1.setText(bundle.getString("MQTTWindow.label1.text"));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                label1.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                send.add(label1);
                label1.setBounds(0, 0, 525, 35);
                send.add(field_message);
                field_message.setBounds(10, 95, 505, 30);

                //---- label2 ----
                label2.setText(bundle.getString("MQTTWindow.label2.text"));
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                send.add(label2);
                label2.setBounds(0, 60, 525, 30);

                //---- button_send ----
                button_send.setText(bundle.getString("MQTTWindow.button_send.text"));
                button_send.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_sendMouseClicked(e);
                    }
                });
                send.add(button_send);
                button_send.setBounds(275, 170, 145, button_send.getPreferredSize().height);

                //---- error ----
                error.setHorizontalAlignment(SwingConstants.CENTER);
                send.add(error);
                error.setBounds(0, 135, 525, 25);

                //---- button_disconnect ----
                button_disconnect.setText(bundle.getString("MQTTWindow.button_disconnect.text"));
                button_disconnect.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_disconnectMouseClicked(e);
                    }
                });
                send.add(button_disconnect);
                button_disconnect.setBounds(110, 170, 145, 30);

                //---- button_exit ----
                button_exit.setText(bundle.getString("MQTTWindow.button_exit.text"));
                button_exit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_exitMouseClicked(e);
                    }
                });
                send.add(button_exit);
                button_exit.setBounds(200, 215, 135, button_exit.getPreferredSize().height);
                send.add(separator1);
                separator1.setBounds(70, 40, 385, 3);

                //---- status ----
                status.setText(bundle.getString("MQTTWindow.status.text"));
                status.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                send.add(status);
                status.setBounds(10, 5, 210, 26);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < send.getComponentCount(); i++) {
                        Rectangle bounds = send.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = send.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    send.setMinimumSize(preferredSize);
                    send.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab(bundle.getString("MQTTWindow.send.tab.title_4"), send);

            //======== connect ========
            {
                connect.setLayout(null);

                //---- label3 ----
                label3.setText(bundle.getString("MQTTWindow.label3.text"));
                label3.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                connect.add(label3);
                label3.setBounds(0, 0, 525, 35);

                //---- label4 ----
                label4.setText(bundle.getString("MQTTWindow.label4.text"));
                connect.add(label4);
                label4.setBounds(90, 50, 75, 30);
                connect.add(field_ip);
                field_ip.setBounds(165, 55, 260, field_ip.getPreferredSize().height);
                connect.add(field_username);
                field_username.setBounds(165, 90, 260, field_username.getPreferredSize().height);
                connect.add(field_channel);
                field_channel.setBounds(165, 160, 260, field_channel.getPreferredSize().height);
                connect.add(field_password);
                field_password.setBounds(165, 125, 260, field_password.getPreferredSize().height);

                //---- label5 ----
                label5.setText(bundle.getString("MQTTWindow.label5.text"));
                connect.add(label5);
                label5.setBounds(90, 85, 75, 30);

                //---- label6 ----
                label6.setText(bundle.getString("MQTTWindow.label6.text"));
                connect.add(label6);
                label6.setBounds(90, 120, 75, 30);

                //---- label7 ----
                label7.setText(bundle.getString("MQTTWindow.label7.text"));
                connect.add(label7);
                label7.setBounds(90, 155, 75, 30);

                //---- button_connect ----
                button_connect.setText(bundle.getString("MQTTWindow.button_connect.text"));
                button_connect.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_connectMouseClicked(e);
                    }
                });
                connect.add(button_connect);
                button_connect.setBounds(170, 220, 170, button_connect.getPreferredSize().height);

                //---- cerror ----
                cerror.setHorizontalAlignment(SwingConstants.CENTER);
                connect.add(cerror);
                cerror.setBounds(0, 195, 525, 26);
                connect.add(separator2);
                separator2.setBounds(70, 40, 385, 3);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < connect.getComponentCount(); i++) {
                        Rectangle bounds = connect.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = connect.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    connect.setMinimumSize(preferredSize);
                    connect.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab(bundle.getString("MQTTWindow.connect.tab.title"), connect);

            //======== settings ========
            {
                settings.setLayout(null);

                //---- label8 ----
                label8.setText(bundle.getString("MQTTWindow.label8.text"));
                label8.setHorizontalAlignment(SwingConstants.CENTER);
                label8.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                settings.add(label8);
                label8.setBounds(0, -5, 525, 45);

                //---- connect_on_startup ----
                connect_on_startup.setText(bundle.getString("MQTTWindow.connect_on_startup.text"));
                settings.add(connect_on_startup);
                connect_on_startup.setBounds(15, 65, 190, 25);

                //---- save_conn_details ----
                save_conn_details.setText(bundle.getString("MQTTWindow.save_conn_details.text"));
                save_conn_details.setSelected(true);
                settings.add(save_conn_details);
                save_conn_details.setBounds(15, 95, 190, 25);
                settings.add(separator3);
                separator3.setBounds(70, 45, 385, 3);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < settings.getComponentCount(); i++) {
                        Rectangle bounds = settings.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = settings.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    settings.setMinimumSize(preferredSize);
                    settings.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab(bundle.getString("MQTTWindow.settings.tab.title"), settings);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(0, 0, 525, 300);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JTabbedPane tabbedPane1;
    public JPanel send;
    public JLabel label1;
    public JTextField field_message;
    public JLabel label2;
    public JButton button_send;
    public JLabel error;
    public JButton button_disconnect;
    public JButton button_exit;
    public JSeparator separator1;
    public JLabel status;
    public JPanel connect;
    public JLabel label3;
    public JLabel label4;
    public JTextField field_ip;
    public JTextField field_username;
    public JTextField field_channel;
    public JPasswordField field_password;
    public JLabel label5;
    public JLabel label6;
    public JLabel label7;
    public JButton button_connect;
    public JLabel cerror;
    public JSeparator separator2;
    public JPanel settings;
    public JLabel label8;
    public JCheckBox connect_on_startup;
    public JCheckBox save_conn_details;
    public JSeparator separator3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
