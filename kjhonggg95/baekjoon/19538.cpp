#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

vector<int> adj[200001];
int rumor[200001]; // 루머를 믿기 시작한 시간
bool vis[200001];
int n, m, x;

int main()
{
    fastio;
    memset(rumor, -1, sizeof(rumor));
    cin >> n;
    for (int i = 1; i <= n; i++)
    {
        while (cin >> x)
        {
            if (x == 0) break;
            adj[i].push_back(x);
        }
    }

    cin >> m;

    queue<pii> q;
    while (m--)
    {
        cin >> x;
        rumor[x] = 0;
        vis[x] = true;
        q.push({x, 0});
    }

    while (!q.empty())
    {
        auto cur = q.front(); q.pop();
        rumor[cur.X] = cur.Y;

        for (auto& nxt : adj[cur.X])
        {
            if (vis[nxt]) continue;

            int cnt = 0, sz = adj[nxt].size();
            for (auto& e : adj[nxt])
                cnt += (rumor[e] != -1);

            if(((sz & 1) && cnt <= sz / 2) || (!(sz & 1) && cnt < sz / 2)) continue;

            vis[nxt] = true;
            q.push({nxt, cur.Y + 1});
        }
    }

    for (int i = 1; i <= n; i++)
        cout << rumor[i] << ' ';
    cout << '\n';
}
