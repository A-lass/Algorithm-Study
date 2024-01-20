#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, v, e, kist, cr, a, b, l;
ll ans;

vector<pii> adj[1005];
int d[1005];

// 다익스트라 O(ElogE)
// 각 집 -> kist, cr
// 집 최대 100
// O(N * ElogE)

int main()
{
    fastio;
    cin >> n >> v >> e >> kist >> cr;
    vector<int> home(n);
    for(int i = 0;i < n;i++)
        cin >> home[i];

    while(e--)
    {
        cin >> a >> b >> l;
        adj[a].push_back({l, b});
        adj[b].push_back({l, a});
    }

    for(auto st : home)
    {
        memset(d, 0, sizeof(d));
        fill(d, d + v + 1, 1e9);
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        d[st] = 0;
        pq.push({d[st], st});

        while(!pq.empty())
        {
            auto cur = pq.top();
            pq.pop();

            if(d[cur.Y] != cur.X) continue;

            for(auto nxt : adj[cur.Y])
            {
                if(d[nxt.Y] <= d[cur.Y] + nxt.X) continue;
                d[nxt.Y] = d[cur.Y] + nxt.X;
                pq.push({d[nxt.Y], nxt.Y});
            }
        }

        ans += (d[kist] == 1e9 ? -1 : d[kist]);
        ans += (d[cr] == 1e9 ? -1 : d[cr]);
    }

    cout << ans << '\n';
}
