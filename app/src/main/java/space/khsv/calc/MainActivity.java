package space.khsv.calc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private EditText inputA, inputB;
    private TextView result;
    private LinearLayout mainLayout;

    private ImageView themeImage;
    private boolean isLightTheme = true;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeImage = findViewById(R.id.themeImage);
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        result = findViewById(R.id.result);
        mainLayout = findViewById(R.id.mainLayout);


        Button addButton = findViewById(R.id.addButton);
        Button subButton = findViewById(R.id.subButton);
        Button mulButton = findViewById(R.id.mulButton);
        Button divButton = findViewById(R.id.divButton);
        Button modButton = findViewById(R.id.modButton);
        Button powButton = findViewById(R.id.powButton);
        Button sqrtButton = findViewById(R.id.sqrtButton);
        Button factButton = findViewById(R.id.factButton);
        Button themeButton = findViewById(R.id.themeButton);

        addButton.setOnClickListener(view -> calculate('+'));
        subButton.setOnClickListener(view -> calculate('-'));
        mulButton.setOnClickListener(view -> calculate('*'));
        divButton.setOnClickListener(view -> calculate('/'));
        modButton.setOnClickListener(view -> calculate('%'));
        powButton.setOnClickListener(view -> calculate('^'));
        sqrtButton.setOnClickListener(view -> calculate('√'));
        factButton.setOnClickListener(view -> calculate('!'));
        themeButton.setOnClickListener(view -> toggleTheme());
    }
    private void calculate(char operator) {
        try {
            double numA = Double.parseDouble(inputA.getText().toString());
            double numB = operator != '!' && operator != '√' ? Double.parseDouble(inputB.getText().toString()) : 0;
            double output = 0;

            switch (operator) {
                case '+': output = numA + numB; break;
                case '-': output = numA - numB; break;
                case '*': output = numA * numB; break;
                case '/': output = numB != 0 ? numA / numB : Double.NaN; break;
                case '%': output = numA % numB; break;
                case '^': output = Math.pow(numA, numB); break;
                case '√': output = Math.sqrt(numA); break;
                case '!': output = factorial((int) numA); break;
            }
            result.setText(String.valueOf(output));
        } catch (NumberFormatException e) {
            result.setText("❌ Error: Wrong input");
        }
    }

    private int factorial(int n) {
        if (n < 0) return -1;
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    private void toggleTheme() {
        if (isLightTheme) {
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.violet));
            themeImage.setImageResource(R.drawable.adtime);
        } else {
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black));
            themeImage.setImageResource(R.drawable.dark);
        }

        isLightTheme = !isLightTheme;
    }
}
