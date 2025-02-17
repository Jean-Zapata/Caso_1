package com.example.ejemplo_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Operaciones_Basicas extends AppCompatActivity implements View.OnClickListener {

    private EditText etDisplay;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;
    private double lastNumber = 0;
    private String lastOperator = "";
    private int parenthesesCount = 0;
    private StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones_basicas);

        etDisplay = findViewById(R.id.etDisplay);
        etDisplay.setShowSoftInputOnFocus(false);

        setNumericButtonListeners();
        setOperationButtonListeners();
        setScientificButtonListeners();
    }

    private void setNumericButtonListeners() {
        int[] numericIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot};
        for (int id : numericIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    private void setOperationButtonListeners() {
        int[] operationIds = {R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnEquals, R.id.btnClear, R.id.btnPlusMinus, R.id.btnParenOpen,
                R.id.btnParenClose, R.id.btnPow};
        for (int id : operationIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    private void setScientificButtonListeners() {
        int[] scientificIds = {R.id.btnSin, R.id.btnCos, R.id.btnTan, R.id.btnLn,
                R.id.btnSquare, R.id.btnSqrt, R.id.btnPi};
        for (int id : scientificIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(@NonNull View view) {
        if (!(view instanceof Button)) return;

        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("C")) {
            clearCalculator();
        }
        else if (buttonText.equals("=")) {
            calculateResult();
        }
        else if (buttonText.equals("±")) {
            changeSign();
        }
        else if (buttonText.equals("(")) {
            handleParenthesis("(");
        }
        else if (buttonText.equals(")")) {
            handleParenthesis(")");
        }
        else if (buttonText.equals("sin") ||
                buttonText.equals("cos") ||
                buttonText.equals("tan") ||
                buttonText.equals("ln") ||
                buttonText.equals("x²") ||
                buttonText.equals("√") ||
                buttonText.equals("π")) {
            handleScientificOperation(buttonText);
        }
        else if (buttonText.equals("+") ||
                buttonText.equals("-") ||
                buttonText.equals("×") ||
                buttonText.equals("÷") ||
                buttonText.equals("xʸ")) {
            handleBasicOperation(buttonText);
        }
        else {
            handleNumericInput(buttonText);
        }
    }

    private void handleParenthesis(String paren) {
        if (paren.equals("(")) {
            parenthesesCount++;
            if (!currentNumber.isEmpty()) {
                expression.append(currentNumber);
                currentNumber = "";
            }
            expression.append("(");
        } else if (paren.equals(")") && parenthesesCount > 0) {
            parenthesesCount--;
            if (!currentNumber.isEmpty()) {
                expression.append(currentNumber);
                currentNumber = "";
            }
            expression.append(")");
        }
        etDisplay.setText(expression.toString());
    }

    private void clearCalculator() {
        currentNumber = "";
        operator = "";
        firstNumber = 0;
        lastNumber = 0;
        lastOperator = "";
        isNewOperation = true;
        parenthesesCount = 0;
        expression.setLength(0);
        etDisplay.setText("0");
    }

    private void calculateResult() {
        if (!operator.isEmpty() && !currentNumber.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;

            if (!lastOperator.isEmpty()) {
                secondNumber = lastNumber;
            }

            try {
                result = performCalculation(firstNumber, secondNumber, operator);
                lastNumber = secondNumber;
                lastOperator = operator;

                etDisplay.setText(formatResult(result));
                currentNumber = String.valueOf(result);
                firstNumber = result;
                isNewOperation = true;
            } catch (ArithmeticException e) {
                etDisplay.setText("Error");
                isNewOperation = true;
            }
        }
    }

    private double performCalculation(double first, double second, String op) {
        switch (op) {
            case "+": return first + second;
            case "-": return first - second;
            case "×": return first * second;
            case "÷":
                if (second == 0) throw new ArithmeticException("División por cero");
                return first / second;
            case "xʸ": return Math.pow(first, second);
            default: return first;
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.8f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }

    private void changeSign() {
        if (!currentNumber.isEmpty()) {
            if (currentNumber.startsWith("-")) {
                currentNumber = currentNumber.substring(1);
            } else {
                currentNumber = "-" + currentNumber;
            }
            etDisplay.setText(currentNumber);
        }
    }

    private void handleScientificOperation(String operation) {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = 0;

            try {
                result = calculateScientificOperation(number, operation);
                currentNumber = String.valueOf(result);
                etDisplay.setText(formatResult(result));
                isNewOperation = true;
            } catch (ArithmeticException e) {
                etDisplay.setText("Error");
                isNewOperation = true;
            }
        }
    }

    private double calculateScientificOperation(double number, String operation) {
        switch (operation) {
            case "sin": return Math.sin(Math.toRadians(number));
            case "cos": return Math.cos(Math.toRadians(number));
            case "tan": return Math.tan(Math.toRadians(number));
            case "ln":
                if (number <= 0) throw new ArithmeticException("Logaritmo inválido");
                return Math.log(number);
            case "x²": return Math.pow(number, 2);
            case "√":
                if (number < 0) throw new ArithmeticException("Raíz negativa");
                return Math.sqrt(number);
            case "π": return Math.PI;
            default: return number;
        }
    }

    private void handleBasicOperation(String operation) {
        if (!currentNumber.isEmpty()) {
            if (!operator.isEmpty()) {
                calculateResult();
            }
            firstNumber = Double.parseDouble(currentNumber);
            operator = operation;
            isNewOperation = true;
        } else if (operator.isEmpty() && firstNumber != 0) {
            operator = operation;
        }
    }

    private void handleNumericInput(String digit) {
        if (isNewOperation) {
            currentNumber = "";
            isNewOperation = false;
        }
        if (digit.equals(".") && currentNumber.contains(".")) {
            return;
        }
        currentNumber += digit;
        etDisplay.setText(currentNumber);
    }
}