package in.cognitia.cognitia18;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by devansh on 9/9/18.
 */

public class EventsCategoryFragment extends Fragment {
    private RecyclerView recyclerView;

    private List<String> mDataS;
    private static final String ARG_TITLE = "title";
    private String title;

    public static EventsCategoryFragment getInstance(String title) {
        EventsCategoryFragment fragment = new EventsCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString(ARG_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_category_fragment_layout, container, false);
        /*TextView textView = (TextView) view.findViewById(R.id.dummyText);

        StringBuilder dummyText = new StringBuilder();
        for (char ch = 'a'; ch != 'z'; ch++) {
            dummyText.append(ch);
            dummyText.append('\n');
        }
        textView.setText(dummyText);*/
        ListView listView = view.findViewById(R.id.dummyList);

        String[] titles = new String[15];
        for (int i = 0; i < titles.length; i++) {
            titles[i] = title;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(adapter);
        return view;
    }
}