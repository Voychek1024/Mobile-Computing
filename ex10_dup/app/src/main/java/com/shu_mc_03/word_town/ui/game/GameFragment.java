package com.shu_mc_03.word_town.ui.game;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.snackbar.Snackbar;
import com.shu_mc_03.word_town.Board;
import com.shu_mc_03.word_town.R;

import java.util.Timer;
import java.util.TimerTask;

public class GameFragment extends Fragment {

    private static final String TAG = "DEBUG";
    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

    private GameViewModel gameViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gameViewModel =
                new ViewModelProvider(this).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        gameViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        fadeOut.setDuration(500);
        fadeOut.setFillAfter(true);
        textView.setAlpha(1.0f);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                textView.startAnimation(fadeOut);
            }
        };
        timer.schedule(task, 1500);
        
        // Get Username
        String username = getActivity().getIntent().getStringExtra("username");
        if (username==null || username.equals("")) {
            username = "";
            Snackbar.make(getActivity().findViewById(R.id.coordinator_layout), "看起来您并未登录\n您可以进行游戏，但是游戏将不会被记录", Snackbar.LENGTH_LONG)
                    .setAction("登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Navigate to self_fragment
                            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.navigation_self);
                            getActivity().getIntent().putExtra("guide","0");
                        }
                    })
                    .show();
        }

        // Buttons binding
        Button button_easy = (Button) root.findViewById(R.id.button_easy);
        Button button_normal = (Button) root.findViewById(R.id.button_normal);
        Button button_hard = (Button) root.findViewById(R.id.button_hard);

        Intent intent_board = new Intent(root.getContext(), Board.class);
        button_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "Easy Mode", Toast.LENGTH_SHORT).show();
                intent_board.putExtra("MODE", 0);
                intent_board.putExtra("username", getActivity().getIntent().getStringExtra("username"));
                startActivity(intent_board);
            }
        });
        button_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "Normal Mode", Toast.LENGTH_SHORT).show();
                intent_board.putExtra("MODE", 1);
                intent_board.putExtra("username", getActivity().getIntent().getStringExtra("username"));
                startActivity(intent_board);
            }
        });
        button_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(root.getContext(), "Hard Mode", Toast.LENGTH_SHORT).show();
                intent_board.putExtra("MODE", 2);
                intent_board.putExtra("username", getActivity().getIntent().getStringExtra("username"));
                startActivity(intent_board);
            }
        });
        return root;
    }
}