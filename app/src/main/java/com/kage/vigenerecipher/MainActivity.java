package com.kage.vigenerecipher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_keyword) EditText mKeywordText;
    @BindView(R.id.et_normal) EditText mNormalText;
    @BindView(R.id.et_encrypt) EditText mEncryptedText;
    private Encryptor mEncryptor;
    private Toast mToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setDefaultKey();
        setDefaultNormalText("Show must go on");
        mEncryptor = new VigenereCipherEncryptor();
    }

    @OnClick(R.id.btn_encrypt)
    void onEncryptButtonClicked() {
        if (mEncryptor.isCorrectKey(getKeyWord())) {
            mEncryptedText.setText(mEncryptor.encrypt(getKeyWord(), getNormalText()));
        } else {
            showErrorToast();
        }
    }

    @OnClick(R.id.btn_decrypt)
    void onDecryptButtonClicked() {
        if(mEncryptor.isCorrectKey(getKeyWord())){
            mNormalText.setText(mEncryptor.decrypt(getKeyWord(), getEncryptedText()));
        } else {
            showErrorToast();
        }
    }

    private void showErrorToast() {
        if (mToast != null) mToast.cancel();

        mToast = Toast.makeText(this, R.string.wrong_keyword, Toast.LENGTH_SHORT);
        mToast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.encryption, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                clearText();
                return true;
            case R.id.action_default_key:
                setDefaultKey();
                return true;
            case R.id.action_default_text_latin:
                setDefaultNormalText("Show must go on");
                return true;
            case R.id.action_default_text_cyrillic:
                setDefaultNormalText("Шоу должно продолжаться");
                return true;
            case R.id.action_default_text_mixed:
                setDefaultNormalText("Группа Queen была основана в 1970 году.");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDefaultKey() {
        mKeywordText.setText("Bohemian Rhapsody");
    }

    private void setDefaultNormalText(String text) {
        mNormalText.setText(text);
        mEncryptedText.setText("");
    }

    private String getKeyWord() {
        return mKeywordText.getText().toString();
    }

    private String getNormalText() {
        return mNormalText.getText().toString();
    }

    private String getEncryptedText() {
        return mEncryptedText.getText().toString();
    }

    private void clearText() {
        mKeywordText.setText("");
        mNormalText.setText("");
        mEncryptedText.setText("");
    }
}
