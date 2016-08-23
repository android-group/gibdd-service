package ru.android_studio.gibdd_servis.question.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.R;

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

//        phoneEditText.setTransformationMethod(new PhoneTransformationMethod(phonePattern));
        phoneEditText.addTextChangedListener(new PhoneFormatValidator());
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

    private class PhoneFormatValidator implements TextWatcher {

        private StringBuilder stringBuilder = new StringBuilder();

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            synchronized (this.getClass()){
                // если идет удаление
                if (before > count) {

                    Integer lenght = before;
                    do {
                        stringBuilder.deleteCharAt(lenght - 1);
                        lenght = stringBuilder.length();
                    } while (lenght > 0 && !Character.isDigit(stringBuilder.charAt(lenght - 1)));

                    phoneEditText.setText(stringBuilder.toString());
                    phoneEditText.setSelection(stringBuilder.length());

                    return;
                } // before > count


                // проверка на превышение длинны вводмого текста
                if (!checkAvailableSize(charSequence, start, count)) {
                    phoneEditText.setError("Some error text");
                    return;
                } else if (phoneEditText.getError() != null)
                    phoneEditText.setError(null);

                Character ch;
                for (Integer i = start; i < phonePattern.length(); i++) {
                    if (Character.isDigit(ch = phonePattern.charAt(i))) {
                        stringBuilder.append(charSequence.charAt(start));
                        break;
                    }

                    stringBuilder.append(ch.charValue());
                }

                phoneEditText.setText(stringBuilder.toString());
                phoneEditText.setSelection(stringBuilder.length());
            } // getCurrentFocus() == phoneEditText
        }

        private boolean checkAvailableSize(CharSequence charSequence, Integer start, Integer count) {

            if(start + count < phonePattern.length()) {
                phoneEditText.setText(charSequence.subSequence(0, start));
                phoneEditText.setSelection(start);
                return false;
            }

            return true;
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
