/*
 * Created by JFormDesigner on Tue May 03 10:42:12 CEST 2022
 */

package util.window;

import util.Send;
import util.Configs;

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
        if (Send.Companion.getClient() == null || !Send.Companion.getClient().isConnected()) {
            error.setText("No Connection to Client/Server");
            return;
        }

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
        Configs.Companion.save();
        this.dispose();
        System.exit(0);
    }

    private void button_subscripeMouseClicked(MouseEvent e) {
        Send.Companion.setSUBSRIBE(field_subsribe.getText());
        Send.Companion.subsrcibe();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("util.window.resources.MQTTWindow");
        tabbedPane2 = new JTabbedPane();
        send2 = new JPanel();
        label12 = new JLabel();
        field_message = new JTextField();
        label13 = new JLabel();
        button_send = new JButton();
        error = new JLabel();
        button_disconnect = new JButton();
        button_exit = new JButton();
        separator6 = new JSeparator();
        status = new JLabel();
        connect2 = new JPanel();
        label14 = new JLabel();
        label15 = new JLabel();
        field_ip = new JTextField();
        field_username = new JTextField();
        field_channel = new JTextField();
        field_password = new JPasswordField();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        button_connect = new JButton();
        cerror = new JLabel();
        separator7 = new JSeparator();
        panel2 = new JPanel();
        label = new JLabel();
        separator8 = new JSeparator();
        label20 = new JLabel();
        field_subsribe = new JTextField();
        button_subscripe = new JButton();
        separator9 = new JSeparator();
        scrollPane2 = new JScrollPane();
        subscribe_log = new JTextArea();
        label2 = new JLabel();
        settings2 = new JPanel();
        label22 = new JLabel();
        connect_on_startup = new JCheckBox();
        save_conn_details = new JCheckBox();
        separator10 = new JSeparator();
        subscribe_on_start = new JCheckBox();

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

        //======== tabbedPane2 ========
        {

            //======== send2 ========
            {
                send2.setLayout(null);

                //---- label12 ----
                label12.setText(bundle.getString("MQTTWindow.label12.text"));
                label12.setHorizontalAlignment(SwingConstants.CENTER);
                label12.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                send2.add(label12);
                label12.setBounds(0, 0, 525, 35);
                send2.add(field_message);
                field_message.setBounds(10, 95, 505, 30);

                //---- label13 ----
                label13.setText(bundle.getString("MQTTWindow.label13.text"));
                label13.setHorizontalAlignment(SwingConstants.CENTER);
                label13.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                send2.add(label13);
                label13.setBounds(0, 60, 525, 30);

                //---- button_send ----
                button_send.setText(bundle.getString("MQTTWindow.button_send.text"));
                button_send.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_sendMouseClicked(e);
                    }
                });
                send2.add(button_send);
                button_send.setBounds(275, 170, 145, button_send.getPreferredSize().height);

                //---- error ----
                error.setHorizontalAlignment(SwingConstants.CENTER);
                send2.add(error);
                error.setBounds(0, 135, 525, 25);

                //---- button_disconnect ----
                button_disconnect.setText(bundle.getString("MQTTWindow.button_disconnect.text"));
                button_disconnect.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_disconnectMouseClicked(e);
                    }
                });
                send2.add(button_disconnect);
                button_disconnect.setBounds(110, 170, 145, 30);

                //---- button_exit ----
                button_exit.setText(bundle.getString("MQTTWindow.button_exit.text"));
                button_exit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_exitMouseClicked(e);
                    }
                });
                send2.add(button_exit);
                button_exit.setBounds(200, 212, 135, button_exit.getPreferredSize().height);
                send2.add(separator6);
                separator6.setBounds(70, 40, 385, 3);

                //---- status ----
                status.setText(bundle.getString("MQTTWindow.status.text"));
                status.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                send2.add(status);
                status.setBounds(10, 5, 210, 26);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < send2.getComponentCount(); i++) {
                        Rectangle bounds = send2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = send2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    send2.setMinimumSize(preferredSize);
                    send2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane2.addTab(bundle.getString("MQTTWindow.send2.tab.title"), send2);

            //======== connect2 ========
            {
                connect2.setLayout(null);

                //---- label14 ----
                label14.setText(bundle.getString("MQTTWindow.label14.text"));
                label14.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                label14.setHorizontalAlignment(SwingConstants.CENTER);
                connect2.add(label14);
                label14.setBounds(0, 0, 525, 35);

                //---- label15 ----
                label15.setText(bundle.getString("MQTTWindow.label15.text"));
                connect2.add(label15);
                label15.setBounds(90, 50, 75, 30);
                connect2.add(field_ip);
                field_ip.setBounds(165, 55, 260, field_ip.getPreferredSize().height);
                connect2.add(field_username);
                field_username.setBounds(165, 90, 260, field_username.getPreferredSize().height);
                connect2.add(field_channel);
                field_channel.setBounds(165, 160, 260, field_channel.getPreferredSize().height);
                connect2.add(field_password);
                field_password.setBounds(165, 125, 260, field_password.getPreferredSize().height);

                //---- label16 ----
                label16.setText(bundle.getString("MQTTWindow.label16.text"));
                connect2.add(label16);
                label16.setBounds(90, 85, 75, 30);

                //---- label17 ----
                label17.setText(bundle.getString("MQTTWindow.label17.text"));
                connect2.add(label17);
                label17.setBounds(90, 120, 75, 30);

                //---- label18 ----
                label18.setText(bundle.getString("MQTTWindow.label18.text"));
                connect2.add(label18);
                label18.setBounds(90, 155, 75, 30);

                //---- button_connect ----
                button_connect.setText(bundle.getString("MQTTWindow.button_connect.text"));
                button_connect.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_connectMouseClicked(e);
                    }
                });
                connect2.add(button_connect);
                button_connect.setBounds(170, 220, 170, button_connect.getPreferredSize().height);

                //---- cerror ----
                cerror.setHorizontalAlignment(SwingConstants.CENTER);
                connect2.add(cerror);
                cerror.setBounds(0, 195, 525, 26);
                connect2.add(separator7);
                separator7.setBounds(70, 40, 385, 3);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < connect2.getComponentCount(); i++) {
                        Rectangle bounds = connect2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = connect2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    connect2.setMinimumSize(preferredSize);
                    connect2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane2.addTab(bundle.getString("MQTTWindow.connect2.tab.title"), connect2);

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- label ----
                label.setText(bundle.getString("MQTTWindow.label.text"));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                panel2.add(label);
                label.setBounds(0, 0, 525, 35);
                panel2.add(separator8);
                separator8.setBounds(70, 40, 385, 3);

                //---- label20 ----
                label20.setText(bundle.getString("MQTTWindow.label20.text"));
                panel2.add(label20);
                label20.setBounds(125, 56, 65, 30);

                //---- field_subsribe ----
                field_subsribe.setText(bundle.getString("MQTTWindow.field_subsribe.text"));
                panel2.add(field_subsribe);
                field_subsribe.setBounds(190, 57, 220, field_subsribe.getPreferredSize().height);

                //---- button_subscripe ----
                button_subscripe.setText(bundle.getString("MQTTWindow.button_subscripe.text"));
                button_subscripe.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button_subscripeMouseClicked(e);
                    }
                });
                panel2.add(button_subscripe);
                button_subscripe.setBounds(190, 90, 100, button_subscripe.getPreferredSize().height);
                panel2.add(separator9);
                separator9.setBounds(0, 144, 525, 3);

                //======== scrollPane2 ========
                {

                    //---- subscribe_log ----
                    subscribe_log.setEditable(false);
                    scrollPane2.setViewportView(subscribe_log);
                }
                panel2.add(scrollPane2);
                scrollPane2.setBounds(50, 155, 465, 102);

                //---- label2 ----
                label2.setText(bundle.getString("MQTTWindow.label2.text"));
                panel2.add(label2);
                label2.setBounds(9, 155, 41, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane2.addTab(bundle.getString("MQTTWindow.panel2.tab.title"), panel2);

            //======== settings2 ========
            {
                settings2.setLayout(null);

                //---- label22 ----
                label22.setText(bundle.getString("MQTTWindow.label22.text"));
                label22.setHorizontalAlignment(SwingConstants.CENTER);
                label22.setFont(new Font("Segoe UI", Font.PLAIN, 30));
                settings2.add(label22);
                label22.setBounds(0, -5, 525, 45);

                //---- connect_on_startup ----
                connect_on_startup.setText(bundle.getString("MQTTWindow.connect_on_startup.text"));
                settings2.add(connect_on_startup);
                connect_on_startup.setBounds(15, 65, 190, 25);

                //---- save_conn_details ----
                save_conn_details.setText(bundle.getString("MQTTWindow.save_conn_details.text"));
                save_conn_details.setSelected(true);
                settings2.add(save_conn_details);
                save_conn_details.setBounds(15, 95, 190, 25);
                settings2.add(separator10);
                separator10.setBounds(70, 45, 385, 3);

                //---- subscribe_on_start ----
                subscribe_on_start.setText(bundle.getString("MQTTWindow.subscribe_on_start.text"));
                settings2.add(subscribe_on_start);
                subscribe_on_start.setBounds(15, 125, 160, 25);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < settings2.getComponentCount(); i++) {
                        Rectangle bounds = settings2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = settings2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    settings2.setMinimumSize(preferredSize);
                    settings2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane2.addTab(bundle.getString("MQTTWindow.settings2.tab.title"), settings2);
        }
        contentPane.add(tabbedPane2);
        tabbedPane2.setBounds(0, 0, 525, 300);

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
    public JTabbedPane tabbedPane2;
    public JPanel send2;
    public JLabel label12;
    public JTextField field_message;
    public JLabel label13;
    public JButton button_send;
    public JLabel error;
    public JButton button_disconnect;
    public JButton button_exit;
    public JSeparator separator6;
    public JLabel status;
    public JPanel connect2;
    public JLabel label14;
    public JLabel label15;
    public JTextField field_ip;
    public JTextField field_username;
    public JTextField field_channel;
    public JPasswordField field_password;
    public JLabel label16;
    public JLabel label17;
    public JLabel label18;
    public JButton button_connect;
    public JLabel cerror;
    public JSeparator separator7;
    public JPanel panel2;
    public JLabel label;
    public JSeparator separator8;
    public JLabel label20;
    public JTextField field_subsribe;
    public JButton button_subscripe;
    public JSeparator separator9;
    public JScrollPane scrollPane2;
    public JTextArea subscribe_log;
    public JLabel label2;
    public JPanel settings2;
    public JLabel label22;
    public JCheckBox connect_on_startup;
    public JCheckBox save_conn_details;
    public JSeparator separator10;
    public JCheckBox subscribe_on_start;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
