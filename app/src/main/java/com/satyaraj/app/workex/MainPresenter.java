package com.satyaraj.app.workex;

import com.satyaraj.app.workex.base.BasePresenter;
import com.satyaraj.app.workex.pojo.Work;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter {

    MainRepository mMainRepository;
    MainActivity mView;
    List<Work> mWorkList = new ArrayList<>();
    List<Work> mMatchedList = new ArrayList<>();

    public MainPresenter(MainActivity activity, MainRepository mainRepository) {
        this.mMainRepository = mainRepository;
        mView = activity;
        mainRepository.onAttach(this);
    }

    public void getList(){
        mMainRepository.makeRequest();
    }

    public void showList(List<Work> workList){
        mWorkList = workList;
        mView.displayList(workList);
    }

    public void searchChar(CharSequence s){
        mMatchedList.clear();
        for (int i = 0;  i < mWorkList.size(); i++){
            if (mWorkList.get(i).getFirstName().toLowerCase().startsWith(s.toString().toLowerCase())){
                mMatchedList.add(mWorkList.get(i));
            }
        }

        mView.displayList(mMatchedList);
    }

}
