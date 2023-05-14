package mask.inc.testgui2;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Testgui2 extends JavaPlugin {

    private JFrame frame;
    private JButton button;

    @Override
    public void onEnable() {
        createGUI();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mgui")) {
            if (sender instanceof Player) {
                openGUI((Player) sender);
            } else {
                sender.sendMessage("This command can only be executed by a player.");
            }
            return true;
        }
        return false;
    }

    private void createGUI() {
        frame = new JFrame("My Plugin GUI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        button = new JButton("Execute Command");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say Hello from GUI!");
            }
        });

        panel.add(button);
        frame.add(panel);
    }

    private void openGUI(Player player) {
        player.sendMessage("Opening GUI...");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
