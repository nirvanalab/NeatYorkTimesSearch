package com.task.vidhurvoora.neatnytviewer.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.task.vidhurvoora.neatnytviewer.Model.ArticleFilterCriteria;
import com.task.vidhurvoora.neatnytviewer.Model.ArticleFilterSettingsManager;
import com.task.vidhurvoora.neatnytviewer.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class ArticleSearchFragment extends DialogFragment implements DatePickerFragment.CustomDateChooseListener {

    private Button btnSaveFilter;
    private EditText etBeginDate;
    private Spinner spinSortOrder;
    ArticleFilterCriteria filterCriteria;

    public interface OnArticleFilterCriteriaUpdateListener {
        void onArticleFilterCriteriaUpdated(ArticleFilterCriteria criteria);
    }

    public OnArticleFilterCriteriaUpdateListener mListener;

    public ArticleSearchFragment() {
        // Required empty public constructor
    }

    public static ArticleSearchFragment newInstance() {
        ArticleSearchFragment fragment = new ArticleSearchFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_article_search, container);
    }


    @Override
    public void onResume() {
        super.onResume();
        float width = getResources().getDimension(R.dimen.search_filter_popup_w);
        float height  = getResources().getDimension(R.dimen.search_filter_popup_h);
        getDialog().getWindow().setLayout((int)width,(int)height);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filterCriteria = ArticleFilterSettingsManager.getSharedInstance().fetchFilterCriteria(getActivity().getApplicationContext());

        //setup save btn
        btnSaveFilter = (Button)view.findViewById(R.id.btnSaveFilter);
        btnSaveFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    //save the criteria
                    mListener.onArticleFilterCriteriaUpdated(filterCriteria);
                    getDialog().dismiss();
                }
            }
        });

        //setup begin date
        etBeginDate = (EditText)view.findViewById(R.id.etBeginDate);
        String queryDate = filterCriteria.getBeginDate();
        if (queryDate != null ) {
            String formattedDate = getFormattedDateFromQueryDate(queryDate);
            etBeginDate.setText(formattedDate);
        }

        etBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDatePicker();
            }
        });

        //setup spinner
        setupSpinner(view);

        //setup checkbox
        setupCheckboxes(view);

        //setup fonts
        setupFontsForUI(view);

    }

    private void setupFontsForUI(View view){
        // Create the TypeFace from the TTF asset
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/alex_brush_reg.ttf");
        // Get access to our View
        TextView tvBeginDateLbl = (TextView) view.findViewById(R.id.tvBeginDateLbl);
        // Assign the typeface to the view
        tvBeginDateLbl.setTypeface(font);
        // Get access to our View
        TextView tvSortOrderLbl = (TextView) view.findViewById(R.id.tvSortOrderLbl);
        // Assign the typeface to the view
        tvSortOrderLbl.setTypeface(font);
        // Get access to our View
        TextView tvNewsDeskLbl = (TextView) view.findViewById(R.id.tvNewsDeskLbl);
        // Assign the typeface to the view
        tvNewsDeskLbl.setTypeface(font);
        // Get access to our View
        EditText etBeginDate = (EditText) view.findViewById(R.id.etBeginDate);
        // Assign the typeface to the view
        etBeginDate.setTypeface(font);
        // Get access to our View
        Button btnSaveFilter = (Button) view.findViewById(R.id.btnSaveFilter);
        // Assign the typeface to the view
        btnSaveFilter.setTypeface(font);
        // Get access to our View
        Spinner spinSortOrder = (Spinner) view.findViewById(R.id.spinSortOrder);
        // Assign the typeface to the view
//        TextView spinView = (TextView)spinSortOrder.getRootView();
//        spinView.setTypeface(font);

        CheckBox cbCategoryArts = (CheckBox) view.findViewById(R.id.cbCategoryArts);
        // Assign the typeface to the view
        cbCategoryArts.setTypeface(font);
        CheckBox cbCategoryFashion = (CheckBox) view.findViewById(R.id.cbCategoryFashion);
        // Assign the typeface to the view
        cbCategoryFashion.setTypeface(font);
        CheckBox cbCategorySports = (CheckBox) view.findViewById(R.id.cbCategorySports);
        // Assign the typeface to the view
        cbCategorySports.setTypeface(font);

    }
    //checkbox listner
    CompoundButton.OnCheckedChangeListener checkListner = new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
            Set<String> newsDeskComponents = filterCriteria.getNewsDeskComponents();
            if (newsDeskComponents == null ) {
                newsDeskComponents = new HashSet<String>();
            }
            String queryComponent = "";
            ArticleFilterSettingsManager filterManager = ArticleFilterSettingsManager.getSharedInstance();
            switch (compoundButton.getId()){
                case R.id.cbCategoryArts:
                    queryComponent = filterManager.getQueryNewsDeskComponent(R.id.cbCategoryArts);
                    break;
                case R.id.cbCategoryFashion:
                    queryComponent = filterManager.getQueryNewsDeskComponent(R.id.cbCategoryFashion);
                    break;
                case R.id.cbCategorySports:
                    queryComponent = filterManager.getQueryNewsDeskComponent(R.id.cbCategorySports);
                    break;
            }
            if (checked) {
                newsDeskComponents.add(queryComponent);
            }
            else {
                newsDeskComponents.remove(queryComponent);
            }
            filterCriteria.setNewsDeskComponents(newsDeskComponents);
            Log.d("Test","Testt");
           // ArticleFilterSettingsManager.getSharedInstance().saveFilterCriteria(filterCriteria,getContext());
        }
    };
    private void setupCheckboxes(View view) {

        CheckBox cbCategoryArts = (CheckBox) view.findViewById(R.id.cbCategoryArts);
        cbCategoryArts.setChecked(false);
        cbCategoryArts.setOnCheckedChangeListener(checkListner);

        CheckBox cbCategoryFashion = (CheckBox) view.findViewById(R.id.cbCategoryFashion);
        cbCategoryFashion.setChecked(false);
        cbCategoryFashion.setOnCheckedChangeListener(checkListner);

        CheckBox cbCategorySports = (CheckBox) view.findViewById(R.id.cbCategorySports);
        cbCategorySports.setChecked(false);
        cbCategorySports.setOnCheckedChangeListener(checkListner);

        Set<String> newsDeskValues = filterCriteria.getNewsDeskComponents();

        if ( newsDeskValues != null ) {
            ArticleFilterSettingsManager filterManager = ArticleFilterSettingsManager.getSharedInstance();
            String artsCategory = filterManager.getQueryNewsDeskComponent(R.id.cbCategoryArts);
            if (artsCategory!=null && newsDeskValues.contains(artsCategory)) {
                cbCategoryArts.setChecked(true);
            }
            String fashionCategory = filterManager.getQueryNewsDeskComponent(R.id.cbCategoryFashion);
            if (fashionCategory!=null && newsDeskValues.contains(fashionCategory)) {
                cbCategoryFashion.setChecked(true);
            }
            String sportsCategory = filterManager.getQueryNewsDeskComponent(R.id.cbCategorySports);
            if (sportsCategory!= null && newsDeskValues.contains(sportsCategory)) {
                cbCategorySports.setChecked(true);
            }
        }
    }

    private void setupSpinner(View view){
        spinSortOrder = (Spinner)view.findViewById(R.id.spinSortOrder);
        String [] items = new String[2];
        items[0]="oldest";
        items[1]="newest";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, items) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
               // v.setBackgroundColor(Color.TRANSPARENT);

                Typeface externalFont=Typeface.createFromAsset(getActivity().getAssets(), "fonts/alex_brush_reg.ttf");
                ((TextView) v).setTypeface(externalFont);

                return v;
            }


            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);

                Typeface externalFont=Typeface.createFromAsset(getActivity().getAssets(), "fonts/alex_brush_reg.ttf");
                ((TextView) v).setTypeface(externalFont);
                //((TextView) v).setTextSize(R.dimen.search_filter_text_size);
                ((TextView) v).setTextSize(35);
                v.setBackgroundColor(Color.BLACK);


                return v;
            }
        };


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSortOrder.setAdapter(adapter);

        if (filterCriteria.getShouldSortByNewest()) {
            spinSortOrder.setSelection(0);
        }
        else {
            spinSortOrder.setSelection(1);
        }
        spinSortOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    filterCriteria.setShouldSortByNewest(true);
                }
                else {
                    filterCriteria.setShouldSortByNewest(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void launchDatePicker() {
        FragmentManager fm = getFragmentManager();
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setTargetFragment(ArticleSearchFragment.this,300);
        datePickerFragment.mListener = this;
        datePickerFragment.existingDate = filterCriteria.getBeginDate();
        datePickerFragment.show(fm,"date picker");
    }

    private String getFormattedDateFromQueryDate(String queryDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dateObj = dateFormat.parse(queryDate);
            dateFormat = new SimpleDateFormat("MMM dd, yyyy");
           return dateFormat.format(dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onDatePicked(String selectedDate) {
        filterCriteria.setBeginDate(selectedDate);
        String formattedDate = getFormattedDateFromQueryDate(selectedDate);
        etBeginDate.setText(formattedDate);
    }


}
