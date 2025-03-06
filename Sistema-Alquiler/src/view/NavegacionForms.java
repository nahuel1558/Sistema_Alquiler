package view;

import javax.swing.*;
import java.util.Stack;

public class NavegacionForms {

    private static final Stack<JFrame> pilaForm = new Stack<>();

    public static void agregarForm(JFrame form) {
        if (!pilaForm.isEmpty()) {
            JFrame formActual = pilaForm.peek();
            formActual.setVisible(false);
        }
        pilaForm.push(form);
        form.setVisible(true);
    }

    public static void volverAlFormAnterior() {
        if (!pilaForm.isEmpty()) {
            JFrame formActual = pilaForm.pop();
            formActual.dispose();
        }
        if (!pilaForm.isEmpty()) {
            JFrame formAnterior = pilaForm.peek();
            formAnterior.setVisible(true);
        }
    }
}
