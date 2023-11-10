#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, a, b, d, rr, gg, mxleaf;
vector<pii> g[200001]; // {dest, dist};
bool vis[200001];

// 기둥 : root ~ giga
// 가지 : giga ~ leaf

void dfs(int cur, int dist)
{
    mxleaf = max(mxleaf, dist);

    if (gg == 0 && g[cur].size() >= 3)
    {
        gg = cur;
        return;
    }

    for (auto nxt : g[cur])
    {
        if (vis[nxt.X]) continue;
        vis[nxt.X] = true;
        dfs(nxt.X, dist + nxt.Y);
    }
}

int main()
{
    fastio;
    cin >> n >> rr;
    for (int i = 0; i < n - 1; i++)
    {
        cin >> a >> b >> d;
        g[a].push_back({b, d});
        g[b].push_back({a, d});
    }

    // 기둥
    vis[rr] = true;
    if(g[rr].size() >= 2) // 루트에서 바로 가지가 나오는 경우
        gg = rr;    // 루트 == 기가
    else
        dfs(rr, 0);
    
    cout << mxleaf << ' ';
    mxleaf = 0;

    // 가지
    vis[gg] = true;
    dfs(gg, 0);
    cout << mxleaf << '\n';
}
