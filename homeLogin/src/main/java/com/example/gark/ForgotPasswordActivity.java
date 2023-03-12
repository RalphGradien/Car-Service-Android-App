public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mEmailEditText = findViewById(R.id.email_edit_text);
        mSubmitButton = findViewById(R.id.submit_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString().trim();

                // Check if email exists in the database
                if (checkEmailExists(email)) {
                    // Generate a new password
                    String newPassword = generateNewPassword();

                    // Update the database with the new password
                    updatePasswordInDatabase(email, newPassword);

                    // Send email to user with the new password
                    sendEmail(email, newPassword);

                    // Show a success message to the user
                    Toast.makeText(ForgotPasswordActivity.this, "New password sent to email", Toast.LENGTH_SHORT).show();

                    // Go back to the login activity
                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Email does not exist in the database, show an error message to the user
                    Toast.makeText(ForgotPasswordActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkEmailExists(String email) {
        // Check if email exists in the database
        // Return true if email exists, false otherwise
    }

    private String generateNewPassword() {
        // Generate a new password and return it
    }

    private void updatePasswordInDatabase(String email, String newPassword) {
        // Update the password for the given email in the database
    }

    private void sendEmail(String email, String newPassword) {
        // Send an email to the given email address with the new password
        // You can use the JavaMail API for this
    }
}
