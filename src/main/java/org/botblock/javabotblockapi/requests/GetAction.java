/*
 * Copyright 2020 Andre601
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.botblock.javabotblockapi.requests;

import org.botblock.javabotblockapi.Site;
import org.botblock.javabotblockapi.annotations.DeprecatedSince;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to perform GET actions with.
 * 
 * <p>With this class can you retrieve information about either a bot, its information on the bot lists, or the bot lists themself.
 * 
 * <p>All requests are cached for 2 minutes. This can be disabled with {@link #GetAction(boolean) GetAction(true)} although it's not recommended.
 * 
 * @since 3.0.0
 * 
 * @deprecated Use {@link org.botblock.javabotblockapi.requests.GetBotAction GetBotAction} for Bot related and
 *             {@link org.botblock.javabotblockapi.requests.GetListAction GetListAction} for List related actions.
 */
@Deprecated
@DeprecatedSince(version = "5.0.0", replacements = {"GetBotAction", "GetListAction"})
public class GetAction{
    
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    private boolean disableCache;
    
    /**
     * Constructor to get the instance of GetAction.
     */
    public GetAction(){
        this.disableCache = false;
    }
    
    /**
     * Constructor to get the instance of GetAction.
     * Use this if you want to disable the caching of the GET requests. <b>We do not recommend this without own caching/ratelimiting.</b>
     * 
     * @param disableCache
     *        Whether or not caching should be disabled. {@code true} means caching is <b>disabled</b>!
     */
    public GetAction(boolean disableCache){
        this.disableCache = disableCache;
    }
    
    /**
     * Gets the full information of a bot.
     *
     * <p>The JSONObject may look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "username": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *             {"data": "Unique bot list data"},
     *             200
     *         ],
     *         "otherlist.org": [
     *             {"data": "Unique bot list data"},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     * <br>The values of id, username, discriminator, owners, server_count and invite are based on the most common appearance.
     * <br>The returned information from the different bot list may vary for each one.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null JSONObject containing the bots information.
     */
    @Nullable
    public JSONObject getBotInfo(Long id){
        return REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
    }
    
    /**
     * Gets the full information of a bot.
     *
     * <p>The JSONObject may look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "username": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *             {"data": "Unique bot list data"},
     *             200
     *         ],
     *         "otherlist.org": [
     *             {"data": "Unique bot list data"},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     * <br>The values of id, username, discriminator, owners, server_count and invite are based on the most common appearance.
     * <br>The returned information from the different bot list may vary for each one.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null JSONObject containing the bots information.
     */
    @Nullable
    public JSONObject getBotInfo(@NotNull String id){
        return REQUEST_HANDLER.performGetBot(id, disableCache);
    }
    
    /**
     * Gets the information of the bot stored on the different bot lists.
     * <br>The returned JSON depends on what each bot list returns and is therefore different for each one.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-null JSONObject containing the bots information from the different bot lists.
     */
    @Nullable
    public JSONObject getBotListInfo(Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getJSONObject("list_data");
    }
    
    /**
     * Gets the information of the bot stored on the different bot lists.
     * <br>The returned JSON depends on what each bot list returns and is therefore different for each one.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-null JSONObject containing the bots information from the different bot lists.
     */
    @Nullable
    public JSONObject getBotListInfo(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getJSONObject("list_data");
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get info from.
     *         
     * @return Possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(Long id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site.getSite());
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The site to get the info from.
     *         <br>A list of supported sites can be found {@link Site here}.
     * 
     * @return Possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(Long id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site);
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get info from.
     *         
     * @return Possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(@NotNull String id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site.getSite());
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The site to get the info from.
     *         <br>A list of supported sites can be found {@link org.botblock.javabotblockapi.Site here}.
     *         
     * @return Possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(@NotNull String id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site);
    }
    
    /**
     * Gets the API information of a specific bot list on botblock.org.
     *
     * <p>The returned JSON could look like this:
     * <br><pre><code>
     * {
     *   "api_docs": "https://thelist.org/api/docs",
     *   "api_post": "https://thelist.org/api/bot/stats/:id",
     *   "api_field": "server_count",
     *   "api_shard_id": "shard_id",
     *   "api_shard_count": "shard_count",
     *   "api_shards": null,
     *   "api_get": "https://thelist.org/api/bot/info/:id"
     * }
     * </code></pre>
     *
     * @param  id
     *         The id used for the internal caching.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null JSONObject containing information from the provided bot list.
     */
    @Nullable
    public JSONObject getBotList(@NotNull String id, @NotNull Site site){
        if(site.equals(Site.DISCORDBOTS_ORG) || site.equals(Site.TOP_GG))
            throw new IllegalStateException("discordbots.org and top.gg are not supported for GetAction!");
        
        return REQUEST_HANDLER.performGetList(id, site.getSite(), disableCache);
    }
    
    /**
     * Gets the API information of a specific bot list on botblock.org.
     *
     * <p>The returned JSON could look like this:
     * <br><pre><code>
     * {
     *   "api_docs": "https://thelist.org/api/docs",
     *   "api_post": "https://thelist.org/api/bot/stats/:id",
     *   "api_field": "server_count",
     *   "api_shard_id": "shard_id",
     *   "api_shard_count": "shard_count",
     *   "api_shards": null,
     *   "api_get": "https://thelist.org/api/bot/info/:id"
     * }
     * </code></pre>
     *
     * @param  id
     *         The id used for the internal caching.
     * @param  site
     *         The site to get information from.
     *         <br>A list of supported sites can be found {@link org.botblock.javabotblockapi.Site here}.
     *
     * @return Possibly-null JSONObject containing information from the provided bot list.
     */
    @Nullable
    public JSONObject getBotList(@NotNull String id, @NotNull String site){
        return REQUEST_HANDLER.performGetList(id, site, disableCache);
    }
    
    /**
     * Gets the API information of all supported bot lists on botblock.org.
     *
     * <p>The returned JSON could look like this:
     * <br><pre><code>
     * {
     *   "thelist.org": {
     *     "api_docs": "https://thelist.org/api/docs",
     *     "api_post": "https://thelist.org/api/bot/stats/:id",
     *     "api_field": "server_count",
     *     "api_shard_id": "shard_id",
     *     "api_shard_count": "shard_count",
     *     "api_shards": null,
     *     "api_get": "https://thelist.org/api/bot/info/:id"
     *   },
     *   "listofbots.com": {
     *     "api_docs": "https://listofbots.com/docs",
     *     "api_post": "https://listofbots.com/api/stats/:id",
     *     "api_field": "guild_count",
     *     "api_shard_id": null,
     *     "api_shard_count": null,
     *     "api_shards": "shards",
     *     "api_get": null
     *   }
     * }
     * </code></pre>
     *
     * @param  id
     *         The id used for the internal caching.
     *
     * @return A Possibly-null JSONObject containing information from all supported bot lists.
     */
    @Nullable
    public JSONObject getBotLists(@NotNull String id){
        return REQUEST_HANDLER.performGetList(id, null, disableCache);
    }
    
    /**
     * Gets the features of a bot list.
     * <br>The displayed features of a bot list can be both positive and negative.
     * 
     * <p>The returned JSONArray may look like this:
     * <br><pre><code>
     * [
     *     {
     *         "name": "Markdown Long Description",
     *         "id": 4,
     *         "display": 4,
     *         "type": 0,
     *         "description": null,
     *         "value": 1
     *     },
     *     {
     *         "name": "Certified Bot Invite Link",
     *         "id": 18,
     *         "display": 3,
     *         "type": 0,
     *         "description": null,
     *         "value": 1
     *     }
     * ]
     * </code></pre>
     * 
     * @param  id
     *         The id used for the internal caching.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *         
     * @return JSONArray containing various information about the specified bot list.
     * 
     * @since  4.3.0
     */
    public JSONArray getBotListFeatures(@NotNull String id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetList(id, site.getSite(), disableCache);
        
        return json.getJSONArray("features");
    }
    
    /**
     * Gets the features of a bot list.
     * <br>The displayed features of a bot list can be both positive and negative.
     *
     * <p>The returned JSONArray may look like this:
     * <br><pre><code>
     * [
     *     {
     *         "name": "Markdown Long Description",
     *         "id": 4,
     *         "display": 4,
     *         "type": 0,
     *         "description": null,
     *         "value": 1
     *     },
     *     {
     *         "name": "Certified Bot Invite Link",
     *         "id": 18,
     *         "display": 3,
     *         "type": 0,
     *         "description": null,
     *         "value": 1
     *     }
     * ]
     * </code></pre>
     *
     * @param  id
     *         The id used for the internal caching.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return JSONArray containing various information about the specified bot list.
     * 
     * @since  4.3.0
     */
    public JSONArray getBotListFeatures(@NotNull String id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetList(id, site, disableCache);
        
        return json.getJSONArray("features");
    }
    
    /**
     * Gets the discriminator (The 4 numbers after the # in the username) of the bot.
     * <br>The discriminator is based on the most common appearance of it across the bot lists.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-empty String containing the Discriminator of the bot.
     * 
     * @since  v4.2.0
     */
    public String getDiscriminator(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("discriminator");
    }
    
    /**
     * Gets the discriminator (The 4 numbers after the # in the username) of the bot.
     * <br>The discriminator is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the Discriminator of the bot.
     *
     * @since  4.2.0
     */
    public String getDiscriminator(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("discriminator");
    }
    
    /**
     * Gets the GitHub link of the bot.
     * <br>The GitHub link is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-empty String containing the GitHub link of the bot.
     * 
     * @since  4.2.0
     */
    public String getGitHub(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("github");
    }
    
    /**
     * Gets the GitHub link of the bot.
     * <br>The GitHub link is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the GitHub link of the bot.
     * 
     * @since  4.2.0
     */
    public String getGitHub(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("github");
    }
    
    /**
     * Gets the OAuth invite of the bot.
     * <br>The invite is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return A String containing the OAuth invite for the bot.
     */
    public String getInvite(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("invite");
    }
    
    /**
     * Gets the OAuth invite of the bot.
     * <br>The invite is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A String containing the OAuth invite for the bot.
     */
    public String getInvite(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("invite");
    }
    
    /**
     * Gets the currently used library of the bot.
     * <br>The library is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-empty String containing the library of the bot.
     * 
     * @since  4.2.0
     */
    public String getLibrary(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("library");
    }
    
    /**
     * Gets the currently used library of the bot.
     * <br>The library is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the library of the bot.
     *
     * @since  4.2.0
     */
    public String getLibrary(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("library");
    }
    
    /**
     * Gets the name of the bot.
     * <br>The name is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-empty String containing the name of the bot.
     * 
     * @since  4.2.0
     */
    public String getName(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("username");
    }
    
    /**
     * Gets the name of the bot.
     * <br>The name is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the name of the bot.
     *
     * @since  4.2.0
     */
    public String getName(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("username");
    }
    
    /**
     * Gets an ArrayList with the owner ids of the bot.
     * <br>The IDs listed are based on how often they appear on the different bot lists.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty ArrayList containing the owners of the bot.
     */
    public List<String> getOwners(Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
    
    /**
     * Gets an ArrayList with the owner ids of the bot.
     * <br>The IDs listed are based on how often they appear on the different bot lists.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty ArrayList containing the owners of the bot.
     */
    public List<String> getOwners(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
    
    /**
     * Gets the prefix of the bot.
     * <br>The prefix is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the prefix of the bot.
     *
     * @since  4.2.0
     */
    public String getPrefix(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
    
        return json.getString("prefix");
    }
    
    /**
     * Gets the prefix of the bot.
     * <br>The prefix is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the prefix of the bot.
     * 
     * @since  v4.2.0
     */
    public String getPrefix(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("prefix");
    }
    
    /**
     * Gets the server count of the bot.
     * <br>The server count is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null Integer containing the server count for the bot.
     */
    @Nullable
    public Integer getServerCount(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
    
        return json.getInt("server_count");
    }
    
    /**
     * Gets the server count of the bot.
     * <br>The server count is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null Integer containing the server count for the bot.
     */
    @Nullable
    public Integer getServerCount(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getInt("server_count");
    }
    
    /**
     * Gets the support link (i.e. Discord invite) from the bot.
     * <br>The link is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-empty String containing the support link.
     */
    public String getSupportLink(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("support");
    }
    
    /**
     * Gets the support link (i.e. Discord invite) from the bot.
     * <br>The link is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the support link.
     */
    public String getSupportLink(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("support");
    }
    
    /**
     * Gets the website of the bot.
     * <br>The website is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-Empty String containing the bot's website.
     * 
     * @since  4.2.0
     */
    public String getWebsite(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("website");
    }
    
    /**
     * Gets the website of the bot.
     * <br>The website is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return Possibly-Empty String containing the bot's website.
     * 
     * @since  4.2.0
     */
    public String getWebsite(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("website");
    }
}
