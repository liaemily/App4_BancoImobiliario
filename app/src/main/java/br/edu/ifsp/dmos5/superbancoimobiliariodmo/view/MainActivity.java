package br.edu.ifsp.dmos5.superbancoimobiliariodmo.view;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmos5.superbancoimobiliariodmo.R;
import br.edu.ifsp.dmos5.superbancoimobiliariodmo.model.StarBank;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText inputEditText;
    private EditText inputEditTextReward;
    private Button roundCompletedButton;
    private Button transferButton;
    private Button payBankButton;
    private Button receiverBankButton;
    private TextView outputTextView;
    private Spinner receiverSpinner;
    private Spinner paySpinner;
    private Spinner rewardSpinner;
    private StarBank bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        inputEditText = findViewById(R.id.edittext_input);
        inputEditTextReward = findViewById(R.id.edittext_input_round_completed);
        roundCompletedButton = findViewById(R.id.button_round_completed);
        transferButton = findViewById(R.id.button_transfer);
        payBankButton = findViewById(R.id.button_pay_bank);
        receiverBankButton = findViewById(R.id.button_receiver_bank);
        outputTextView = findViewById(R.id.textview_output);

        receiverSpinner = findViewById(R.id.spin_receiver);
        ArrayAdapter<CharSequence> receiverAdapter = ArrayAdapter.createFromResource(this,
                R.array.receiver_array, android.R.layout.simple_spinner_item);
        receiverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        receiverSpinner.setAdapter(receiverAdapter);

        paySpinner = findViewById(R.id.spin_pay);
        ArrayAdapter<CharSequence> payAdapter = ArrayAdapter.createFromResource(this,
                R.array.pay_array, android.R.layout.simple_spinner_item);
        payAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paySpinner.setAdapter(payAdapter);

        rewardSpinner = findViewById(R.id.spin_reward);
        ArrayAdapter<CharSequence> rewardAdapter = ArrayAdapter.createFromResource(this,
                R.array.reward_array, android.R.layout.simple_spinner_item);
        receiverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rewardSpinner.setAdapter(receiverAdapter);

        roundCompletedButton.setOnClickListener(this);
        transferButton.setOnClickListener(this);
        payBankButton.setOnClickListener(this);
        receiverBankButton.setOnClickListener(this);

        bank = StarBank.getInstance();
    }

    @Override
    public void onClick(View view) {

        if (view == transferButton) {
            processTransfer();
        } else if (view == payBankButton) {
            processPayBank();
        } else if (view == receiverBankButton) {
            processReceiverBank();
        } else if (view == roundCompletedButton) {
            processRoundCompleted();
        }
    }

    private void processTransfer() {

        double value;
        boolean transfer;
        String s = inputEditText.getText().toString();

        try {
            value = Double.valueOf(s);
        } catch (NumberFormatException nfException) {
            value = 0;
            Toast.makeText(this, getString(R.string.invalid_value_message), Toast.LENGTH_SHORT).show();
        }

        String receiver = receiverSpinner.getSelectedItem().toString();
        String pay = paySpinner.getSelectedItem().toString();

        transfer = bank.transfer(parseInt(pay), parseInt(receiver), value);

        if (transfer) {
            outputTextView.setText(String.format("Transferência realizada"));
        } else {
            outputTextView.setText(String.format("Saldo insuficiente"));
        }
    }

    private void processPayBank() {

        double value;
        boolean payBank;
        String s = inputEditText.getText().toString();

        try {
            value = Double.valueOf(s);
        } catch (NumberFormatException nfException) {
            value = 0;
            Toast.makeText(this, getString(R.string.invalid_value_message), Toast.LENGTH_SHORT).show();
        }

        String pay = paySpinner.getSelectedItem().toString();

        payBank = bank.pay(parseInt(pay), value);

        if (payBank) {
            outputTextView.setText(String.format("Transferência realizada"));
        } else {
            outputTextView.setText(String.format("Saldo insuficiente"));
        }
    }

    private void processReceiverBank() {
        double value;

        String s = inputEditText.getText().toString();

        try {
            value = Double.valueOf(s);
        } catch (NumberFormatException nfException) {
            value = 0;
            Toast.makeText(this, getString(R.string.invalid_value_message), Toast.LENGTH_SHORT).show();
        }

        String receiver = receiverSpinner.getSelectedItem().toString();

        bank.receive(parseInt(receiver), value);

        outputTextView.setText(String.format("Transferência realizada"));
    }

    private void processRoundCompleted() {
        double value;

        String s = inputEditTextReward.getText().toString();

        try {
            value = Double.valueOf(s);
        } catch (NumberFormatException nfException) {
            value = 0;
            Toast.makeText(this, getString(R.string.invalid_value_message), Toast.LENGTH_SHORT).show();
        }

        String receiver = receiverSpinner.getSelectedItem().toString();

        bank.roundCompleted(parseInt(receiver), value);

        outputTextView.setText(String.format("Recompensa creditada"));
    }
}