/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import models.Game;
import models.Dealer;
import models.Player;

import com.google.inject.Singleton;
import ninja.params.PathParam;


@Singleton
public class ApplicationController {

    public Result index() {

        return Results.html();

    }
    
    public Result helloWorldJson() {
        
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello World! Hello Json!";

        return Results.json().render(simplePojo);

    }
    
    public static class SimplePojo {

        public String content;
        
    }
    public Result game() {
        return Results.html().template("views/game/game.flt.html");
    }

    public Result gameGet(){
        Game g = new Game();
        g.buildDeck();
        g.shuffle();
        g.P_value=g.player.calculate();
        g.D_value=g.dealer.calculate_d();
        return Results.json().render(g);
    }
    public Result start (Context context, Game g){
        if(context.getRequestPath().contains("Start")) {
            g.deal_player();
            g.deal_player();
            g.deal_dealer();
            g.deal_dealer();
            g.place_bet(2);
            g.P_value=g.player.calculate();
            g.D_value=g.dealer.calculate_d();
            if (g.P_value==21){
                g.assign_bet();
                g.user_win=1;
                g.bet_amount=0;
            }
        }
        return Results.json().render(g);
    }
    public Result dealPost(Context context, Game g) {
        if(context.getRequestPath().contains("deal")){
            g.deal_player();
            g.P_value=g.player.calculate();
            g.D_value=g.dealer.calculate_d();
            if(g.check_brust(g.player.calculate()))
            {
                g.user_win=2;
                if (!g.split) {
                    g.bet_amount=0;
                }
            }
            else if (g.P_value==21){
                g.assign_bet();
                g.user_win=1;
                g.bet_amount=0;
            }
        }
        return Results.json().render(g);
    }
    public Result dealPost_s(Context context, Game g) {
        if(context.getRequestPath().contains("dealGame2")){
            g.deal_player_s();
            g.P_value_s=g.player_s.calculate();
            g.D_value=g.dealer.calculate_d();
            if(g.check_brust(g.player_s.calculate()))
            {
                g.player.bet-=g.bet_amount;
                g.user_win_s=2;
            }
            else if (g.P_value_s==21){
                g.assign_bet();
                g.user_win_s=1;
            }
        }
        return Results.json().render(g);
    }
    public Result Split (Context context, Game g) {
        if(context.getRequestPath().contains("split")){
            g.split();
            g.P_value=g.player.calculate();
            g.P_value_s=g.player_s.calculate();
            g.D_value=g.dealer.calculate_d();
            if (g.P_value==21){
                g.assign_bet();
                g.user_win=1;
            }
            if (g.P_value_s==21){
                g.assign_bet();
                g.user_win_s=1;
            }
        }
        return Results.json().render(g);
    }
    public Result Stand(Context context, Game g) {
        if(context.getRequestPath().contains("stand")){
            if (g.split==false || g.player_s.card_count!=0)
                while (g.dealer.calculate_d() < 17) {
                    g.deal_dealer();
                }
            if (g.compare() == 1) {
                g.assign_bet();
                g.user_win = 1;
            } else if (g.compare() == 0) {
                g.player.bet += g.bet_amount;
                g.user_win = 3;
            } else {
                g.user_win = 2;
            }
            if (g.player_s.card_count!=0){
                if (g.compare_s() == 1) {
                    g.assign_bet();
                    g.user_win_s = 1;
                } else if (g.compare_s() == 0) {
                    g.player.bet += g.bet_amount;
                    g.user_win_s = 3;
                } else {
                    g.user_win_s = 2;
                }
            }
            g.P_value = g.player.calculate();
            g.D_value = g.dealer.calculate_d();
            g.P_value_s = g.player_s.calculate();
        }
        return Results.json().render(g);
    }
    public Result Double(Context context, Game g) {
        if(context.getRequestPath().contains("double")){
            g.place_bet(g.bet_amount);
            g.deal_player();
            g.P_value=g.player.calculate();
            if (g.P_value==21){
                g.assign_bet();
                g.user_win=1;
            }
            else if (g.P_value>21){
                g.user_win=2;
            }else {
                while (g.dealer.calculate_d() < 17) {
                    g.deal_dealer();
                }
                g.D_value = g.dealer.calculate_d();
                if (g.compare() == 1) {
                    g.assign_bet();
                    g.user_win = 1;
                } else if (g.compare() == 2) {
                    g.user_win = 2;
                } else {
                    g.player.bet += g.bet_amount;
                    g.user_win = 3;
                }
            }/*  g.buildDeck();
            g.shuffle();
            g.player.holder.get(1).clear();
            // g.player.holder.get(1).clear();
            g.dealer.holder.get(1).clear();*/
        }
        return Results.json().render(g);
    }
    public Result Double_s(Context context, Game g) {
        if(context.getRequestPath().contains("double_s")){
            g.place_bet(g.bet_amount);
            g.deal_player_s();/*
            g.P_value_s=g.player.calculate();
            if (g.P_value_s==21){
                g.assign_bet();
                g.user_win=1;
            }
            else if (g.P_value_s>21){
                g.user_win=2;
            }else {
                while (g.dealer.calculate_d() < 17) {
                    g.deal_dealer();
                }
                g.D_value = g.dealer.calculate_d();
                if (g.compare() == 1) {
                    g.assign_bet();
                    g.user_win = 1;
                } else if (g.compare() == 2) {
                    g.user_win = 2;
                } else {
                    g.player.bet += g.bet_amount;
                    g.user_win = 3;
                }
            }/*  g.buildDeck();
            g.shuffle();
            g.player.holder.get(1).clear();
            // g.player.holder.get(1).clear();
            g.dealer.holder.get(1).clear();*/
        }
        return Results.json().render(g);
    }
    public Result Restart (Context context, Game g){
        if(context.getRequestPath().contains("reStart")) {
            g.buildDeck();
            g.shuffle();
            g.bet_amount=0;
            g.player.holder.get(0).clear();
            g.player.card_count=0;
            g.player.Ace=false;
            g.dealer.holder.get(0).clear();
            g.dealer.card_count=0;
            g.split=false;
            g.bet_amount=0;
            g.player_s.holder.get(0).clear();
            g.player_s.card_count=0;
            g.user_win=0;
            g.P_value=0;
            g.D_value=0;
            g.P_value_s=0;
            g.user_win_s=0;
        }
        return Results.json().render(g);
    }
}
