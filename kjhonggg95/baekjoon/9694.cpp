#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int t, n, m, u, v, w;
int d[21];
int prv[21];

void print(int cur)
{
    if (cur == -1)
        return;
    print(prv[cur]);
    cout << cur << ' ';
}

int main()
{
    fastio;
    cin >> t;
    for (int tc = 1; tc <= t; tc++)
    {
        cin >> n >> m;
        fill(d, d + m + 1, 1e9);
        fill(prv, prv + m + 1, -1);

        vector<pii> adj[m];

        while (n--)
        {
            cin >> u >> v >> w;
            adj[u].push_back({w, v});
            adj[v].push_back({w, u});
        }

        priority_queue<pii, vector<pii>, greater<pii>> pq;
        d[0] = 0;

        pq.push({d[0], 0});

        while (!pq.empty())
        {
            auto cur = pq.top();
            pq.pop();

            if (d[cur.Y] != cur.X) continue;

            for (auto nxt : adj[cur.Y])
            {
                if (d[nxt.Y] <= d[cur.Y] + nxt.X) continue;
                d[nxt.Y] = d[cur.Y] + nxt.X;
                prv[nxt.Y] = cur.Y;

                pq.push({d[nxt.Y], nxt.Y});
            }
        }

        cout << "Case #" << tc << ": ";
        if (d[m - 1] == 1e9)
            cout << -1;
        else
            print(m - 1);
        cout << '\n';
    }
}
