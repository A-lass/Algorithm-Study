#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int p[30];

int find(int a)
{
    if (p[a] < 0) return a;
    return p[a] = find(p[a]);
}

bool isDiff(int a, int b)
{
    a = find(a); b = find(b);

    if (a == b) return false;

    p[b] = a;
    return true;
}

int main()
{
    fastio;
    int n;
    while (cin >> n)
    {
        if(n == 0) break;
        memset(p, -1, sizeof(p));
        vector<pair<int, pii>> v;
        int cnt = 0, ans = 0;

        // 간선 정보 입력(양방향)
        for (int i = 0; i < n - 1; i++)
        {
            char src, dst;
            int k, w;
            cin >> src >> k;
            while (k--)
            {
                cin >> dst >> w;
                v.push_back({w, {src - 'A', dst - 'A'}});
            }
        }

        sort(v.begin(), v.end());

        for(auto e : v)
        {
            if(!isDiff(e.Y.X, e.Y.Y)) continue;
            
            cnt++;
            ans += e.X;
            
            if(cnt == n - 1) break;
        }

        cout << ans << '\n';
    }
}
