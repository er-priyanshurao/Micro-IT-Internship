import javax.swing.*;
import java.awt.*;

public class ToDoListApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;

    public ToDoListApp() {
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 18));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskList);

        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton markDoneButton = new JButton("Mark as Done");

        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        markDoneButton.addActionListener(e -> markTaskAsDone());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(markDoneButton);
        buttonPanel.add(deleteButton);

        add(taskInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement("☐ " + task); // ☐ for incomplete
            taskInput.setText("");
        }
    }

    private void deleteTask() {
        int selected = taskList.getSelectedIndex();
        if (selected != -1) {
            taskListModel.remove(selected);
        }
    }

    private void markTaskAsDone() {
        int selected = taskList.getSelectedIndex();
        if (selected != -1) {
            String task = taskListModel.getElementAt(selected);
            if (task.startsWith("☐")) {
                taskListModel.set(selected, task.replace("☐", "✔")); // ✔ for done
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoListApp().setVisible(true);
        });
    }
}
