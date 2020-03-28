package com.example.azakary.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import static com.example.azakary.R.*;

public class TasbehActivity extends AppCompatActivity {
    //ِ Ui components
    private TextView mTasbehaTxt, mTotTasbehTxt, hadithZeker_field, bottemHeader_field;
    private ImageView mResetImage;
    private Spinner mSpinner;
    private LinearLayout layoutBottomSheet;
    private BottomSheetBehavior sheetBehavior;

    // Vars
    private int mCounter = 0;
    private int mTotCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_tasbeh);

        final Drawable imgDropDown = getApplicationContext().getResources().getDrawable( drawable.ic_dropdown);
        imgDropDown.setBounds( 0, 0, 60, 60 );

        final Drawable imgUpword = getApplicationContext().getResources().getDrawable( drawable.ic_upwordarrow);
        imgUpword.setBounds( 0, 0, 60, 60 );

        mTasbehaTxt = findViewById(id.tasbeha_count);
        mTotTasbehTxt = findViewById(id.total_count);
        mResetImage = findViewById(id.reset);

        mSpinner = findViewById(id.onSpiner);

        hadithZeker_field = findViewById(id.hadithOfzeker);
        bottemHeader_field = findViewById(id.bottemHeader);

        layoutBottomSheet = findViewById(id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                  @Override
                  public void onStateChanged(@NonNull View bottomSheet, int newState) {
                      switch (newState) {
                          case BottomSheetBehavior.STATE_HIDDEN:
                              break;
                          case BottomSheetBehavior.STATE_EXPANDED: {
                              bottemHeader_field.setCompoundDrawables(null, null, imgDropDown, null);
                              mSpinner.setVisibility(View.INVISIBLE);
                              mResetImage.setVisibility(View.INVISIBLE);
                          }
                          break;
                          case BottomSheetBehavior.STATE_COLLAPSED: {
                              bottemHeader_field.setCompoundDrawables(null, null, imgUpword, null);
                              mSpinner.setVisibility(View.VISIBLE);
                              mResetImage.setVisibility(View.VISIBLE);

                          }
                          break;
                          case BottomSheetBehavior.STATE_DRAGGING:
                              break;
                          case BottomSheetBehavior.STATE_SETTLING:
                              break;
                      }
                  }

                  @Override
                  public void onSlide(@NonNull View view, float v) {

                  }
              });

        /**When Click on Text, Expand/Collapse Bottom Sheet**/
        bottemHeader_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    bottemHeader_field.setCompoundDrawables(null, null, imgDropDown, null);
                    mSpinner.setVisibility(View.INVISIBLE);
                    mResetImage.setVisibility(View.INVISIBLE);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    bottemHeader_field.setCompoundDrawables(null, null, imgUpword, null);
                    mSpinner.setVisibility(View.VISIBLE);
                    mResetImage.setVisibility(View.VISIBLE);
                }
            }
        });


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCounter = 0;
                mTasbehaTxt.setText(""+mCounter);

                String selectedItemText = (String) parent.getItemAtPosition(position);

                switch (selectedItemText){
                    case "سبحان الله وبحمده":
                        hadithZeker_field.setText("أخرج الإمام \u200Fالبخاري في صحيحه من حديث أبي هريرة \u200Fرضي الله عنه \u200F \u200Fأن رسول الله \u200F \u200Fصلى الله عليه وسلم \u200F \u200Fقال: \"\u200F \u200Fمن قال سبحان الله وبحمده في يوم مائة مرة حطت خطاياه وإن كانت مثل زبد البحر\"");
                        break;
                    case "لا اله الا لله وحده لا شريك له له الملك وله الحمد":
                        hadithZeker_field.setText("روى البخاري (3293) ومسلم (2691) من حديث أَبِي هُرَيْرَةَ رَضِيَ اللَّهُ عَنْهُ أَنَّ رَسُولَ اللَّهِ صَلَّى اللَّهُ عَلَيْهِ وَسَلَّمَ \n" +
                                "قَالَ : ( مَنْ قَالَ لَا إِلَهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ ، فِي يَوْمٍ مِائَةَ مَرَّةٍ ؛ كَانَتْ لَهُ عَدْلَ عَشْرِ رِقَابٍ ، وَكُتِبَ لَهُ مِائَةُ حَسَنَةٍ ، وَمُحِيَتْ عَنْهُ مِائَةُ سَيِّئَةٍ ، وَكَانَتْ لَهُ حِرْزًا مِنْ الشَّيْطَانِ يَوْمَهُ ذَلِكَ حَتَّى يُمْسِيَ ، وَلَمْ يَأْتِ أَحَدٌ بِأَفْضَلَ مِمَّا جَاءَ ، إِلَّا رَجُلٌ عَمِلَ أَكْثَرَ مِنْهُ ) .");
                        break;
                    case "الله اكبر":
                        hadithZeker_field.setText("عَنْ أُمِّ هَانِئٍ بِنْتِ أَبِي طَالِبٍ رضي الله عنها قَالَتْ:\n" +
                                "\n" +
                                "مَرَّ بِي رَسُولُ اللهِ صلّى الله عليه وسلّم ذَاتَ يَوْمٍ، فَقُلْتُ: يَا رَسُولَ اللهِ، قَدْ كَبِرْتُ، وَضَعُفْتُ - أَوْ كَمَا قَالَتْ -، فَمُرْنِي بِعَمَلٍ أَعْمَلُهُ وَأَنَا جَالِسَةٌ. قَالَ:\n" +
                                "\n" +
                                "(( سَبِّحِي اللهَ مِائَةَ تَسْبِيحَةٍ، فَإِنَّهَا تَعْدِلُ لَكِ مِائَةَ رَقَبَةٍ تَعْتِقِينَهَا مِنْ وَلَدِ إِسْمَاعِيلَ.\n" +
                                "وَاحْمَدِي اللهَ مِائَةَ تَحْمِيدَةٍ، تَعْدِلُ لَكِ مِائَةَ فَرَسٍ مُسْرَجَةٍ مُلْجَمَةٍ تَحْمِلِينَ عَلَيْهَا فِي سَبِيلِ اللهِ.\n" +
                                "وَكَبِّرِي اللَّهَ مِائَةَ تَكْبِيرَةٍ، فَإِنَّهَا تَعْدِلُ لَكِ مِائَةَ بَدَنَةٍ مُقَلَّدَةٍ مُتَقَبَّلَةٍ، وَهَلِّلِي اللَّهَ مِائَةَ تَهْلِيلَةٍ - قَالَ ابْنُ خَلَفٍ: أَحْسِبُهُ قَالَ:- تَمْلَأُ مَا بَيْنَ السَّمَاءِ وَالْأَرْضِ، وَلَا يُرْفَعُ يَوْمَئِذٍ لِأَحَدٍ عَمَلٌ، إِلَّا أَنْ يَأْتِيَ بِمِثْلِ مَا أَتَيْتِ ))");
                        break;
                    case "لا حول ولا قوه الا بالله العلى العظيم":
                        hadithZeker_field.setText("جاء في فضائل «لا حول ولا قوة إلا بالله العلي العظيم» شيءٌ كثيرٌ؛ فمن ذلك ما أخرجه الطبراني وابن عساكر عن ابن عباس رضي الله عنهما أنه قال: قال رسول الله صلى الله عليه وآله وسلم: «أَكْثِرُوا مِنْ قَوْلِ لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ؛ فَإِنَّهَا مِنْ كُنُوزِ الْجَنَّةِ... فَإِنَّهَا تَدْفَعُ تِسْعَةً وَتِسْعِينَ بَابًا مِنَ الضُّرِّ، أَدْنَاهَا الْهَمَّ وَالْفَقْرَ».\n" +
                                "\n" +
                                "ومن ذلك ما أخرجه الطبراني وابن عساكر عن أبي هريرة رضي الله عنه أنه قال: قال رسول الله صلى الله عليه وآله وسلم: «وَمَنْ أَبْطَأَ عَنْهُ رِزْقُهُ فَلْيُكْثِرْ مِنْ قَوْلِ: لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللهِ».\n" +
                                "\n" +
                                "ومن ذلك ما رواه ابن أبي الدنيا بسنده إلى رسول الله صلى الله عليه وآله وسلم أنه قال: «مَنْ قال: لا حَوْلَ ولا قُوَّةَ إلا باللهِ مائَةَ مَرَّةٍ في كلِّ يَومٍ، لمْ يُصِبْهُ فقْرٌ أَبدًا».");
                        break;
                    case "استغفر الله العظيم":
                        hadithZeker_field.setText("وعن أبي هريرة رضي الله عنه أن رسول الله صلى الله عليه وسلم قال : ( إِنِّي لَأَسْتَغْفِرُ اللَّهَ عَزَّ وَجَلَّ وَأَتُوبُ إِلَيْهِ كُلَّ يَوْمٍ مِائَةَ مَرَّةٍ ) رواه النسائي في \"السنن الكبرى\" (6/114) وأحمد في \"المسند\" (2/450) وحسنه محققو المسند");
                        break;
                    case "اللهم صل وسلم وبارك على سيدنا محمد":
                        hadithZeker_field.setText("قال النبي صلى الله عليه وسلم : \" من صلى علي في يوم مائة مرة، قضى الله له مائة حاجة : سبعين منها الآخرته وثلاثين منها لدنياه”. رواه ابن النجار عن جابر رضي الله عنه٠\n" +
                                "وقال النبي صلى الله عليه وسلم : \" من صلى علي في يوم ألف مرة، لم يمت حتى يبشر بالجنة”. رواه أبو الشيخ عن أنس رضي الله عنه");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void onClick(View v) {
        if(v.getId() == id.sebhaBtn){
            mCounter++;
            mTotCounter++;
            mTasbehaTxt.setText(""+mCounter);
            mTotTasbehTxt.setText(""+mTotCounter);
        }else if (v.getId() == id.reset){
            mCounter = 0;
            mTotCounter = 0;
            mTasbehaTxt.setText(""+mCounter);
            mTotTasbehTxt.setText(""+mTotCounter);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TasbehActivity.this,AzkarCounterScrean.class);
        intent.putExtra("TOTAL_TASBIH",mTotCounter);
        startActivity(intent);
        finish();
    }
}
