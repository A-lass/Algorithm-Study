#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int main()
{
    fastio;
    int n, q;
    cin >> n >> q;
    vector<int> v(n+1);

    // 중력의 영향을 받기 때문에 내림차순으로 입력이 들어온다
    for(int i = 1;i<=n;i++)
        cin >> v[i];
    
    while(q--)
    {
        int x, y;
        cin >> x >> y;

        int wcnt = v[y] - x + 1; // 가로방향 레이저
        int hcnt = 0; // 세로방향 레이저 -> y층 이상에서 x 보다 크거나 같은 최대 층 구하기

        int lo = y - 1, hi = n + 1;

        while(lo + 1 < hi)
        {
            int mid = (lo + hi) / 2;

            if(v[mid] >= x)
                lo = mid;
            else
                hi = mid;
        }

        hcnt = lo - y + 1;

        cout << max(0, wcnt + hcnt - 1) << '\n';
    }
}
