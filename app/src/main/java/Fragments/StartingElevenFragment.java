package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gffecfl.Adapter.TeamListAdapter;
import com.example.gffecfl.Objects.Players;
import com.example.gffecfl.Objects.SquadPlayers;
import com.example.gffecfl.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartingElevenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartingElevenFragment extends Fragment {

    ListView listView;
    String teamName=null;
    List<Players> squadList= new ArrayList<>();
    Map<String,Players> playersMap = new HashMap<>();
    TeamListAdapter adapter;
    TextView numberPlayers , budgetSpent , budgetLeft , points;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartingElevenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartingElevenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartingElevenFragment newInstance(String param1, String param2) {
        StartingElevenFragment fragment = new StartingElevenFragment();
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
        if(getArguments()!=null){
            teamName=getArguments().getString("teamName");
        }
        return inflater.inflate(R.layout.fragment_starting_eleven, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.listStartingHome);
        numberPlayers = (TextView) getView().findViewById(R.id.numberOfPlayers);
        budgetSpent = (TextView) getView().findViewById(R.id.budgetSpent);
        budgetLeft = (TextView) getView().findViewById(R.id.budgetLeft);
        points = (TextView) getView().findViewById(R.id.statPoints);

        if(teamName != null){
            getAllPlayers();
        }
        else {
            String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

            DatabaseReference referenceTeam = FirebaseDatabase.getInstance().getReference();
            DatabaseReference teamReference = referenceTeam.child("Teams").child(uid).child("Name");

            teamReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    teamName = snapshot.getValue(String.class);
                    getAllPlayers();
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }
    }

    private void getAllPlayers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference playersReference = reference.child("Players");
        if(playersReference != null) {
            playersReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Players player = dataSnapshot.getValue(Players.class);
                        playersMap.put(player.getName(), player);
                    }
                    populateListView();
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }

    }



    //This method is called only after teamName variable has been set
    private void populateListView() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference squadReference = reference1.child("Squads").child(teamName);

        if(squadReference!= null) {
            squadReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    squadList.clear();
                    Double spent = 0.0;
                    int totalPoints = 0;
                    int count = 0;
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        SquadPlayers squadPlayer = dataSnapshot.getValue(SquadPlayers.class);
                        String squadPlayername = squadPlayer.getName();
                        Players player = playersMap.get(squadPlayername);
                        if (squadPlayer.getInStartingEleven().equals("Yes")) {
                            squadList.add(player);
                        }

                        spent += Double.parseDouble(player.getSellingPrice());
                        totalPoints += Integer.parseInt(player.getPoints());
                        count++;
                    }
                    Double left = (300.0 - spent);
                    budgetSpent.setText(Double.toString(spent));
                    budgetLeft.setText(Double.toString(left));
                    points.setText(Integer.toString(totalPoints));
                    numberPlayers.setText(Integer.toString(count));

                    adapter = new TeamListAdapter(getContext(), squadList);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }
    }
}