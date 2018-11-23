package tytusnawara.funkcjakwadratowa;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nishant.math.MathView;

import java.util.ArrayList;

public class MainActivity extends Activity {


    MathView mVa, mVb;

    MathView x1, x2;

    EditText eTa;
    EditText eTb;
    EditText eTc;

    Button liczButton;

    MathView[] mVObliczenia = new MathView[17];


    private TextWatcher textWatcher;

    double a = 1;
    double b = 6;
    double c = -4;

    FunkcjaKwadratowa funkcjaKwadratowa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FunkcjaKwadratowa.iniciate_sObliczenia(mVObliczenia.length);

        liczButton = findViewById(R.id.licz);

        eTa = findViewById(R.id.eta);
        eTb = findViewById(R.id.etb);
        eTc = findViewById(R.id.etc);

        mVa = findViewById(R.id.mVa);
        mVb = findViewById(R.id.mVb);

        x1 =findViewById(R.id.x1);
        x2 =findViewById(R.id.x2);



        mVObliczenia[0] = findViewById(R.id.obliczenia0);
        mVObliczenia[1] = findViewById(R.id.obliczenia1);
        mVObliczenia[2] = findViewById(R.id.obliczenia2);
        mVObliczenia[3] = findViewById(R.id.obliczenia3);
        mVObliczenia[4] = findViewById(R.id.obliczenia4);
        mVObliczenia[5] = findViewById(R.id.obliczenia5);
        mVObliczenia[6] = findViewById(R.id.obliczenia6);
        mVObliczenia[7] = findViewById(R.id.obliczenia7);
        mVObliczenia[8] = findViewById(R.id.obliczenia8);
        mVObliczenia[9] = findViewById(R.id.obliczenia9);
        mVObliczenia[10] = findViewById(R.id.obliczenia10);
        mVObliczenia[11] = findViewById(R.id.obliczenia11);
        mVObliczenia[12] = findViewById(R.id.obliczenia12);
        mVObliczenia[13] = findViewById(R.id.obliczenia13);
        mVObliczenia[14] = findViewById(R.id.obliczenia14);
        mVObliczenia[15] = findViewById(R.id.obliczenia15);
        mVObliczenia[16] = findViewById(R.id.obliczenia16);


        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                x1.setText("");
                x2.setText("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

                //to pozwala okreslicz czy policzenie funkcji kwadratowej
                //nie potrwa zbyt dlugo, a jesli tak
                //wyswietla guzik aby upłynnic dzialanie aplikacji
                int sumNumbersLength = 0;

                sumNumbersLength += eTa.getText().toString().length();
                sumNumbersLength += eTb.getText().toString().length();
                sumNumbersLength += eTc.getText().toString().length();

                if(sumNumbersLength < 10)
                {
                    liczButton.setVisibility(View.INVISIBLE);
                    try {
                        liczFunkcjeKwadratowa();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,
                                "Nieznany błąd",
                                Toast.LENGTH_SHORT).show();
                    }

                }

                else
                {
                    FunkcjaKwadratowa.clear_sObliczenia();

                    setmVObliczenia();

                    liczButton.setVisibility(View.VISIBLE);
                    liczButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            liczButton.setVisibility(View.INVISIBLE);
                            try {
                                liczFunkcjeKwadratowa();
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this,
                                        "Nieznany błąd",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                //}
                //else
                //{
                 //   Toast.makeText(MainActivity.this,
                 //           "Za duże liczby",
                 //           Toast.LENGTH_SHORT).show();
                //}


            }
        };

        eTa.addTextChangedListener(textWatcher);
        eTb.addTextChangedListener(textWatcher);
        eTc.addTextChangedListener(textWatcher);



        mVa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        mVb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        x1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        x2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        mVa.setText("`x^2 +`");
        mVb.setText("`x^* +`");

        x1.setText("`x_1=-3 + sqrt 13`");
        x2.setText("`x_2 = -3 - sqrt 13`");

        for(int i = 0; i< mVObliczenia.length; i++)
        {
            //mVObliczenia[i].setText("");

            mVObliczenia[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

            liczFunkcjeKwadratowa();
        }




    }

    private void liczFunkcjeKwadratowa()
    {
        boolean isDecimal = false;

        try{

            a = Double.parseDouble(eTa.getText().toString());
        }catch (Exception e)
        {
            a = 1;
        }

        if(!(a % 1 == 0))
        {
            isDecimal = true;
        }

        try{
            b = Double.parseDouble( eTb.getText().toString());
        }catch (Exception e)
        {
            b = 0;
        }

        if(!(b % 1 == 0))
        {
            isDecimal = true;
        }

        try{
            c = Double.parseDouble( eTc.getText().toString());
        }

        catch (Exception e)
        {
            c = 0;
        }

        if(!(c % 1 == 0))
        {
            isDecimal = true;
        }

        if (a == 0)
        {
            FunkcjaKwadratowa.isFunkcjaKwadratowa = false;
        }
        else
        {
            FunkcjaKwadratowa.isFunkcjaKwadratowa = true;
        }

        //int min = -1000000;
        //int max =  1000000;

        //if(c > min && c < max && a > min &&
        //  a < max && b > min && b < max)
        //{
        if(FunkcjaKwadratowa.isFunkcjaKwadratowa)
        {
            if (isDecimal)
            {
                funkcjaKwadratowa = new FunkcjaKwadratowa(a, b, c);
            }

            else
            {
                funkcjaKwadratowa = new FunkcjaKwadratowa((int) a,
                        (int) b , (int) c);
            }


            Log.i("wartosci", a + " " +b+" "+c);
        }

        setmVObliczenia();

        x1.setText(funkcjaKwadratowa.getX1());
        x2.setText(funkcjaKwadratowa.getX2());
    }

    private void setmVObliczenia()
    {
        for (int j = 0; j < mVObliczenia.length; j++)
        {
            mVObliczenia[j].setText("");
        }

        for(int i = 0; i < FunkcjaKwadratowa.liczbaLinijek; i++)
        {
            mVObliczenia[i].setText(FunkcjaKwadratowa.sObliczenia[i]);
        }
    }


}
