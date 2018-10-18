package in.cognitia.cognitia18;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by devansh on 17/10/18.
 */

public class SearchableActivity extends AppCompatActivity {

    private ArrayList<CognitiaTeamMember> searchedMembers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searchable);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            searchedMembers = TeamMembersArrayInitializer.getMembersByName(query);

            displaySearchResults();
        }
    }

    private void displaySearchResults() {
        RecyclerView rv = findViewById(R.id.rv_searched_team_members);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CognitiaEventDetailsTeamAdapter(this, searchedMembers));
    }
}
