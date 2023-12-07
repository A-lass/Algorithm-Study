#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, k, prv, cnt, p_idx, idx;

int p[1000001];
vector<int> adj[1000001];

int main()
{
    fastio;
    while (cin >> n >> k)
    {
        if(n == 0 && k == 0) return 0;
        cnt = p_idx = 0;
        idx = 1;
        memset(p, 0, sizeof(p));

        vector<int> v(n);

        for (int i = 0; i < n; i++)
            cin >> v[i];

        prv = v[1] - 1;

        while(idx < n)
        {
            auto cur = v[idx++];

            if(prv + 1 != cur)
                p_idx++;
            
            prv = cur;

            p[cur] = v[p_idx];
            adj[p[cur]].push_back(cur);
        }

        for (auto& par : adj[p[p[k]]])
            cnt += (par == p[k] ? 0 : adj[par].size());
        cout << cnt << '\n';

        for(auto& e : v)
            adj[e].clear();
    }
}
