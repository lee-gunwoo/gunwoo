package example.expense.user.app.expense;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import example.expense.user.app.ExpenseList;
import example.expense.user.app.R;
import example.expense.user.app.common.ErrorUtils;

/**
 * Created by dilky on 2016-07-20.
 * 신청하기 화면
 */
public class NewExpense extends AppCompatActivity {


    EditText etPaymentStoreName;
    EditText etPaymentAmount;
    EditText etPaymentDate;

    static final int DATE_DIALOG_ID = 0;

    public int year, month, day;
    private int mYear, mMonth, mDay;

    public NewExpense() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            setContentView(R.layout.content_new_expense);

            addToolBar();
            etPaymentStoreName = (EditText) findViewById(R.id.et_PaymentStoreName);
            etPaymentAmount = (EditText) findViewById(R.id.et_PaymentAmount);
            etPaymentDate = (EditText) findViewById(R.id.et_PaymentDate);

            etPaymentDate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    showDialog(DATE_DIALOG_ID);
                }
            });

        } catch (Exception e) {
            ErrorUtils.AlertException(this, getString(R.string.error_msg_default_with_activity), e);
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int yearSelected,
                              int monthOfYear, int dayOfMonth) {
            year = yearSelected;
            month = monthOfYear + 1;
            day = dayOfMonth;
            Toast.makeText(getApplicationContext(), "날짜: "+year+"-"+month+"-"+day,
                    Toast.LENGTH_SHORT).show();
            etPaymentDate.setText(year + " - " + month + " - " + day);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    private void addToolBar() throws Exception {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.text_new_expense);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void toolbarRightSaveClick(View view) {
        try {
            // Check Empty Value
            if (TextUtils.isEmpty(etPaymentStoreName.getText())
                    || TextUtils.isEmpty(etPaymentAmount.getText())
                    || TextUtils.isEmpty(etPaymentDate.getText())) {
                Toast.makeText(getApplicationContext(), "필수항목을 입력해주세요.\n(사용처.사용금액.사용일자)", Toast.LENGTH_SHORT).show();

                return;
            }

            Intent intent = new Intent(this, ExpenseList.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            ErrorUtils.AlertException(this, getString(R.string.error_msg_default_with_activity), e);
        }
    }


}