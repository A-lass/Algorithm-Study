#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

#define A 1
#define B 2
#define INF 1e9

int n, m, j, k, x, u, v, w;
int d[5005];

map<int, int> house;

vector<pii> adj[5005];

int main()
{
    fastio;
    cin >> n >> m >> j >> k;
    fill(d, d + n + 1, INF);
    
    // A형 집
    for(int i = 0;i<k;i++)
    {
        cin >> x;
        house[x] = A;
    }

    // B형 집
    for(int i = 0;i<k;i++)
    {
        cin >> x;
        house[x] = B;
    }

    while(m--)
    {
        cin >> u >> v >> w;
        adj[u].push_back({w, v});
        adj[v].push_back({w, u});
    }

    priority_queue<pii, vector<pii>, greater<pii>> pq;

    d[j] = 0;
    pq.push({d[j], j});

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

    int min_dist = INF - 1;
    int ans = -1;

    for(int i = 1;i<=n;i++)
    {
        if(house[i] == B && d[i] < min_dist)
        {
            ans = B;
            min_dist = d[i];
        }
        
        if(house[i] == A && d[i] <= min_dist)
        {
            ans = A;
            min_dist = d[i];
        }
    }

    if(ans == -1)
        cout << ans << '\n';
    else
        cout << (ans == A ? 'A' : 'B') << '\n' << min_dist << '\n';
}
