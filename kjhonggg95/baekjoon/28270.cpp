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
    int n, x;
    cin >> n;

    stack<pii> s;
    s.push({0, 0});
    vector<int> ans;
    while(n--)
    {
        cin >> x;

        if(s.top().X + 2 <= x)
        {
            cout << -1 << '\n';
            return 0;
        }

        if(s.top().X >= x)
        {
            if(s.top().X > x) while(s.top().X != x) s.pop();
            ans.push_back(s.top().Y + 1);
            s.push({x, s.top().Y + 1});
        }
        else if(s.top().X + 1 == x)
        {
            ans.push_back(1);
            s.push({x, 1});
        }  
    }   

    for(auto& e : ans)
        cout << e << ' ';
    return 0;
}
