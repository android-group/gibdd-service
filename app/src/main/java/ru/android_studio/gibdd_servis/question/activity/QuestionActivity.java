package ru.android_studio.gibdd_servis.question.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.utils.textpatterns.PhoneTransformationMethod;

/**
 * Created by y.andreev on 03.06.2016.
 *
 * Прием обращений
 * Направление обращения в госавтоинспекцию
 * Обращения, направленные в электронном виде через официальный сайт Госавтоинспекции (www.gibdd.ru),
 * поступают в выбранное подразделение Госавтоинспекции на региональном уровне или
 * непосредственно в ГУОБДД МВД России.
 Перед отправкой электронного обращения необходимо корректно заполнить анкету.
 */
public class QuestionActivity  extends ActivityWithMenu implements View.OnClickListener {

    @BindView(R.id.btn_next)
    Button nextBtn;

    @BindView(R.id.PhoneEditText)
    EditText phoneEditText;

    @BindString(R.string.phonePattern)
    String phonePattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.ic_question);
        setMenuConfig();

        nextBtn.setOnClickListener(this);

        phoneEditText.setTransformationMethod(new PhoneTransformationMethod(phonePattern));
        phoneEditText.addTextChangedListener(new TextWatcher() {

            String resultString = "";
            final Integer[] keyDel = new Integer[]{0};

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean isTextIncorrect;
                String phoneStr = phoneEditText.getText().toString();

                if(phoneStr.length() > phonePattern.length())
                    phoneEditText.setError("Error message here!");

                int j = 0;
                while(j < phoneStr.length()) {
                    if (!Character.isDigit(phonePattern.charAt(j)) && (phoneStr.charAt(j) != phonePattern.charAt(j)))
                        break;
                    ++j;
                }

                isTextIncorrect = (j >= phoneStr.length());

                if (isTextIncorrect) {

                    phoneEditText.setOnKeyListener(new View.OnKeyListener() {

                        @Override
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {
                            keyDel[0] = (i == KeyEvent.KEYCODE_DEL) ? 1 : 0;
                            return false;
                        }
                    });

                    String str = "";
                    if (keyDel[0] == 0) {
                        final int pos = phoneEditText.getText().length();

                        if(pos < phonePattern.length() && !Character.isDigit(phonePattern.charAt(pos))) {
                            str = phoneEditText.getText().toString() + Character.toString(phonePattern.charAt(pos));
                            phoneEditText.setText(str);
                        }

                    } else {
                        keyDel[0] = 0;
                        str = phoneEditText.getText().toString();
                        str = str.substring(0, str.length() - 1);
                        phoneEditText.setText(str);
                        phoneEditText.setSelection(str.length());
                    }

                    phoneEditText.setSelection(str.length());
                    resultString = str;


                }
                else {
                    phoneEditText.setText(resultString);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_question_btn;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next :
                Intent intent = new Intent(this, QuestionActivityNext.class);
                startActivity(intent);
                break;
        }
    }
}
