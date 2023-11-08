#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, m, a, o, p, q;
int par[100001];
int arr[100001];
bool live[100001];

// find
int find(int u)
{
    if(par[u] < 0) return u;
    return par[u] = find(par[u]);
}

// 동맹
void align(int u, int v)
{
    u = find(u);
    v = find(v);

    if(u < v)
    {
        par[v] = u;
        arr[u] += arr[v];
        live[v] = false;
    }
    else
    {
        par[u] = v;
        arr[v] += arr[u];
        live[u] = false;
    }
}

// 속국
void merge(int u, int v)
{
    u = find(u);
    v = find(v);

    if(u == v) return;

    par[v] = u;
}

int main()
{
    fastio;
    memset(par, -1, sizeof(par));
    memset(live, true, sizeof(live));
    cin >> n >> m;
    for(int i = 1;i<=n;i++)
        cin >> arr[i];
    
    while(m--)
    {
        cin >> o >> p >> q;
        
        if(o == 1) // 동맹
            align(p, q);
        else if(o == 2) // 전쟁
        {
            p = find(p);
            q = find(q);

            if(arr[p] == arr[q])
            {
                arr[p] = arr[q] = 0;
                live[p] = live[q] = false;
            }
            else
            {   
                int winner = (arr[p] > arr[q] ? p : q);
                int loser = (arr[p] > arr[q] ? q : p);

                arr[winner] = arr[winner] - arr[loser];
                live[loser] = false;

                merge(winner, loser);
            }
        }
    }

    vector<int> ans;
    for(int i = 1;i<=n;i++)
        if(live[i])
            ans.push_back(arr[i]);

    sort(ans.begin(), ans.end());
    
    cout << ans.size() << '\n';

    for(auto e : ans)
        cout << e << ' ';
    cout << '\n';
}
