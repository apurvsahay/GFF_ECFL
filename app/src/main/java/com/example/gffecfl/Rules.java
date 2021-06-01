package com.example.gffecfl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Rules extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        textView =(TextView) findViewById(R.id.rules);
        imageView = (ImageView) findViewById(R.id.backRules);
//        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setText("GFF ECFL Rules and other Details:\n\n" +
                "1.\tAuction to be commenced as early as possible after the UCL final (Tentative date: 2nd June, 2021). Each position’s players will be auctioned together and the order of the auction will be Goalkeepers first, then Defenders, Midfielders, finally Forwards. At all times, the auction will be commenced alphabetically. As midfielders have the most number of players, they will be auctioned at the weekend. Time of every auction will take place as decided within the Participants group. A time will be suggested by the moderator, and in accordance with that time at least 1 member from each team should be present. If one member each from 14 teams is present then that time will be fixed for auction.\n\n" +
                "2.\tA link for a separate auction group will be provided by one of the admins, where everybody can join but only 1 team member can participate in the auction. Only that member will be made admin in the auction group along with the moderator (total 15 admins) and the settings of the group will be changed to ‘messages can be sent by admins only’; others can watch the auction.\n\n" +
                "3.\tBase price of every player is set to a default value by their position in the official Euro fantasy app. The base prices by position are given below:\n\n" +
                "i.\tGoalkeeper – 4.5m\n" +
                "ii.\tDefender – 4.5m\n" +
                "iii.\tMidfielder – 5m\n" +
                "iv.\tForward – 6m\n\n" +
                "4.\tDuring the auction, the moderator will announce a player’s name and wait for the participants’ message. If no bid is made within 20 seconds, the player will be sold to the team that made the last bid, or will go unsold (if no bid is made at all for the player). You can get an added 30 seconds, but please keep in mind to use this as minimal as possible as this can affect the overall time of the auction. Teams using the 30 second buffer too much will be given a warning by the moderator. 3 warnings will mean disqualification.\n\n" +
                "5.\tMessages that can be sent by the participants in the auction after the moderator announces a player’s name and base price:\n\n" +
                "i.\tPrice you want to bid against a player\n" +
                "ii.\t‘OUT’\n" +
                "iii.\t‘Need 30 seconds’\n\n" +
                "For the ‘price you want to bid against a player’ point, you can raise a player’s bidding price by 0.5m only, till the price reaches 10m, after that you will be able to increase the player’s price by 1m only.\n" +
                "Please refrain from sending any extra messages other than these, or moderator will give a warning. Disqualification rule always remains the same – after 3 warnings.\n\n" +
                "6.\tYou have to select a total of 15 players in your team, no more, no less, throughout the whole Euro Cup auctions at every phase. You will have a budget of 300m. Select your players sensibly, because if you don’t have enough budget to complete your squad you will be disqualified. Moderator will let you know your budget after every auction day or/and after every round by position, whichever comes first. Moderator will also give you a notice if you are close to ‘disqualification due to insufficient funds’ after these rounds.\n\n" +
                "7.\tDuring the auction, if two or more teams send the same message of the price they want to bid against a player at the same time, only the message the moderator received first will be taken into account. He will let everyone know with whom the player is during this kind of confusion, after displaying a message ‘STOP’. No bids will be approved after the ‘STOP’ message from moderator, until and unless he announces the team name whose bid is currently in, and he displays a message ‘START’.\n\n" +
                "8.\tWithin the 15 members squad, every team should be divided by position in the following manner:\n\n" +
                "i.\tGoalkeepers - 2\n" +
                "ii.\tDefenders - 5\n" +
                "iii.\tMidfielders - 5\n" +
                "iv.\tForwards - 3\n\n" +
                "9.\tThere is a restriction rule on the number of players that can be selected from a particular country for each phase (such as max 3 players from same country during phase - group stages). Please look into the Euro fantasy official app for these basic rules. Every basic rule related with squad selection, point distribution, substitution, etc will all be the same in this competition, until and unless any rule stated here overrides the basic rule.\n\n" +
                "10.\tIn the group stages after MD1 and MD2, teams can use their 2 free transfer rule (from official app) only during the time if a player from his team gets injured. This rule cannot be used in any other situation such as player getting a red card and getting suspended. To use this benefit, teams need to let know the moderator about the player(s) injured and the player name(s) he would like to replace. The transferred in player(s) should fit in the budget remaining (budget remaining = budget freed up after selling injured player(s) + budget remaining at the end of initial auction).\n\n" +
                "11.\tAuction will take place in 5 phases overall. These are:\n\n" +
                "i.\tBefore Euro Cup begins\n" +
                "ii.\tWithin group stage and Round of 16\n" +
                "iii.\tWithin Round of 16 and Quarterfinal\n" +
                "iv.\tWithin Quarterfinal and Semifinal\n" +
                "v.\tWithin Semifinal and Final\n\n" +
                "No other auction phase will take place in between any matchday other than these. Only time an additional mini-auction can take place (between MD1 and MD2, and between MD2 and MD3) if players from more than 2 teams are injured and those teams want a same player to replace the injured one.\n\n" +
                "12.\tBefore every phase of auction starts, total budget, points, players from each teams will be reverted back to default for every team. Hence, budget at the start of any auction phase will be 300m, points will be 0, and players will be reset to none. When a player will be bid at during any auction, bidding will always start from the default base price and not the selling price in the last phase. This default base price will always remain the same throughout the competition.\n\n" +
                "13.\tAfter all the rounds in a single phase of auction are completed, the unsold players can be bid at again if any team is yet to complete their 15 players’ squad. This time the base price of every player unsold will be reduced by 0.5m from the default base price (Note: the default base price will still remain the same for the next auction phases). Teams need to submit the names of the players they want to bid at, to the moderator personally. He will make a list of all the players that will be re-auctioned and will commence the last round of auction.\n\n" +
                "14.\tNumber of teams that will go through to the next rounds are given below:\n\n" +
                "i.\tGroup Stage to R16s - 9\n" +
                "ii.\tR16s to Quarterfinals - 6\n" +
                "iii.\tQuarterfinals to Semifinals - 4\n" +
                "iv.\tSemifinals to Final - 2\n\n" +
                "This will be a league based fantasy league. Top 9 teams from group stages will go to the Round of sixteen, 6 teams from Round of sixteen to Quarterfinals, and so on.\n\n" +
                "15.\tAn app is provided to all, where you will be able to see the points table, the full squad along with the first XI for a particular day in any matchday. The substitutions that you wish to do should be told to the moderator, who in turn will make the necessary changes, and this will reflect in the first XI when you log in. Also, a duplicate team must be made in the official fantasy app by any one member from your team in accordance with your team made via auction. Here, you will receive the points for each player which the moderator will update in the app you are provided with. You should also update the substitutions in the official app as the moderator will completely follow the official app to collect points. Before the auction begins, it is recommended to create a dummy team in the official app beforehand, and to change a player as and when a new player entries your team (This is recommended so that the rule of ‘particular number of players from a single country’ is not avoided during auction, which can lead to disqualification). A link will be provided to join the GFF Support League in the official app, where the moderator will also join with a dummy team to keep an eye on everybody’s points. In the case of any budget issue to create your 15-man squad in official app, select 14 players in accordance to your team, and 1 dummy player. Mention to the moderator about the dummy player’s name and the player whom the dummy player represents. The moderator will keep a tab on the points those players only, who are replaced by dummy players due to budget issue.\n\n" +
                "16.\tTiebreakers, if it comes down to elimination of a team will be based on the following factors (points are ordered priority-wise, for e.g., point 1 will be considered first, if still tied then point 2):\n\n" +
                "i.\tHighest total number of goals scored by your starting XI\n" +
                "ii.\tLeast total number of goals conceded by your starting XI\n" +
                "iii.\tLeast red cards in your starting XI\n" +
                "iv.\tLeast yellow cards in your starting XI\n" +
                "v.\tA virtual coin toss (will be held live if it comes down to this lol)\n");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rules.this,HomeActivity.class);
                Rules.this.startActivity(intent);
            }
        });
    }
}