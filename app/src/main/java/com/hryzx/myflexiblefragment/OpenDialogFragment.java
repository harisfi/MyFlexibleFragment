package com.hryzx.myflexiblefragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RadioGroup rgOptions;
    RadioButton rbSaf, rbMou, rbLvg, rbMoyes;
    Button btnClose, btnChoose;
    OnOptionDialogListener optionDialogListener;

    public OpenDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenDialogFragment newInstance(String param1, String param2) {
        OpenDialogFragment fragment = new OpenDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super .onViewCreated(view, savedInstanceState);
        rgOptions = view.findViewById(R.id.rg_options);
        rbSaf = view.findViewById(R.id.rb_saf);
        rbLvg = view.findViewById(R.id.rb_lvg);
        rbMou = view.findViewById(R.id.rb_mou);
        rbMoyes = view.findViewById(R.id.rb_moyes);

        btnChoose = view.findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int chkRadioBtnId = rgOptions.getCheckedRadioButtonId();
                if (chkRadioBtnId != -1) {
                    String coach;
                    switch (chkRadioBtnId) {
                        case R.id.rb_saf:
                            coach = rbSaf.getText().toString().trim();
                            break;
                        case R.id.rb_lvg:
                            coach = rbLvg.getText().toString().trim();
                            break;
                        case R.id.rb_mou:
                            coach = rbMou.getText().toString().trim();
                            break;
                        case R.id.rb_moyes:
                            coach = rbMoyes.getText().toString().trim();
                            break;
                        default:
                            coach = null;
                            break;
                    }
                    if (optionDialogListener != null) {
                        optionDialogListener.onOptionChosen(coach);
                    }
                    getDialog().dismiss();
                }
            }
        });

        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super .onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof DetailCategoryFragment) {
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.optionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super .onDetach();
        this.optionDialogListener = null;
    }

    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }


}