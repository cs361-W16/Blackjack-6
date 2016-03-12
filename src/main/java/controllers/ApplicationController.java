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
}
